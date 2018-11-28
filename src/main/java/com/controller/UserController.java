package com.controller;


import com.aliyuncs.exceptions.ClientException;
import com.cache.Memcached;
import com.model.User;
import com.service.UserServiceImpl;
import com.tps.TPS;
import com.utility.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@Controller
public class UserController {


    @Autowired
    private UserServiceImpl userService;
    private Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    Log log;
    @Autowired
    TPS tps;


    @RequestMapping(value = "/secondPage", method = RequestMethod.GET)
    public String test2() {
        return "secondPage";
    }


    //邮箱注册页面
    @RequestMapping(value = "/emailRegister", method = RequestMethod.GET)
    public String test1() {
        return "emailRegister";
    }

    //手机注册页面
    @RequestMapping(value = "/phoneRegister", method = RequestMethod.GET)
    public String register() {
        return "phoneRegister";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String page( ){
        return "phoneRegister";
    }


    //注册账号
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView addUser(User user, String code, ModelAndView modelAndView) throws Exception {
        logger.info("注册内容:" + user);
        logger.info("验证码：" + code);
        User messages;
        String message;
        String view;
        String code1;
        if (user.getPhone() != null) {
            message = user.getPhone();
            messages = userService.showByPhone(user.getPhone());
            code1 = String.valueOf(Memcached.getInstance().get(user.getPhone()));

        } else {
            message = user.getPostbox();
            messages = userService.showByPostbox(user.getPostbox());
            code1 = String.valueOf(Memcached.getInstance().get(user.getPostbox()));

        }

        logger.info("缓存中取出的验证码：" + code1);

        if (userService.showOne(user.getUsername()) != null) {
            modelAndView.addObject("message",user.getUsername());
            view = "repetition";
        } else if (messages != null) {
            modelAndView.addObject("message", message);
            view = "repetition";
        } else {
            if (code.equals(code1)) {
                String password = MD5str.getMD5Str(user.getUsername() + user.getPassword());
                logger.info("加密后的密码：" + password);
                user.setPassword(password);
                logger.info(user);
                long id = userService.add(user);
                logger.info("注册成功，用户ID为：" + id);
                view = "turnLogin";
            } else {

                view = "codeError";
            }
        }
        modelAndView.setViewName(view);
        return modelAndView;
    }


    //发送手机验证码
    @ResponseBody
    @RequestMapping(value = "/phoneCode", method = RequestMethod.POST)
    public Map codeDemo(String mobile) throws ClientException {
        logger.info(mobile);
        Map<String, Object> map = new HashMap<>();
        //判断手机号是否为空
        if (mobile == null) {
            map.put("num", "k");
        } else {
            //判断手机号是否已被注册
            if (userService.showByPhone(mobile) != null) {
                logger.info("手机号已被注册");
                map.put("num", "0");
                logger.info(map.get("num"));
            } else {

                //调用第三方API发送验证码
                String code = Aliyun.duanxin(mobile);
                switch (code) {
                    case "0":
                        map.put("num", "限流");
                        break;
                    case "-1":
                        map.put("num", "失败");
                        break;
                    default:
                        logger.info("发送的验证码：" + code);

                        //将验证码添加到缓存中，失效时间为5分钟。
                        Memcached.getInstance().add(mobile, code, new Date(1000 * 60 * 5));
                        map.put("num", "1");
                        break;
                }
            }
        }
        return map;
    }

    //发送邮箱验证码
    @ResponseBody
    @RequestMapping(value = "/emailCode", method = RequestMethod.POST)
    public Map mailDemo(String postbox) throws IOException {
        logger.info(postbox);

        Map<String, Object> map = new HashMap<>();
        if (userService.showByPostbox(postbox) != null) {
            logger.info("邮箱已被注册");
            map.put("num", "0");
            logger.info(map.get("num"));
        } else {

            //调用第三方API发送验证码
            String code = SendMail.send(postbox);
            logger.info(code);
            switch (code) {
                case "-1":
                    map.put("num", "失败");
                    break;
                default:
                    logger.info("发送的验证码：" + code);

                    //将验证码添加到缓存中，失效时间为5分钟。
                    Memcached.getInstance().add(postbox, code, new Date(1000 * 60 * 5));
                    map.put("num", "1");
                    break;
            }
        }
        return map;
    }

    //登录页面
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String test11() {
        return "login";
    }


    //登录账号
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView logs(HttpServletResponse response, User user, ModelAndView modelAndView, DESUtil desUtil) throws Exception {
        logger.info(user);
        String view = log.log(user, desUtil, response);
        modelAndView.setViewName(view);
        return modelAndView;
    }


    //修改个人头像
    @RequestMapping(value = "/headShot", method = RequestMethod.GET)
    public String demo1() {
        return "headShot";
    }

    @RequestMapping(value = "/headShot", method = RequestMethod.POST)
    public String demo2(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String url = tps.tps(file);
        Cookie nameCookie = Cookies.getCookieByName(request, "name");
        Cookie pictureCookie = Cookies.getCookieByName(request, "picture");
        assert pictureCookie != null;
        pictureCookie.setValue(url);
        response.addCookie(pictureCookie);
        assert nameCookie != null;
        String name = nameCookie.getValue();

        User user = userService.showOne(name);
        System.out.println(user);
        user.setPicture(url);
        userService.updateByName(user);
        System.out.println(url);
        return "redirect:/firstPage";
    }


    //退出登录
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String exit(HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie[] = request.getCookies();

        if (cookie != null && cookie.length > 0) {
            for (Cookie c : cookie) {
                System.out.println(c.getName());
                if (c.getName().equals("name")) {
                    c.setMaxAge(0);
                    response.addCookie(c);
                    System.out.println("删除cookie");
                }
            }
        }
        return "redirect:/firstPage";

    }

}
