package com.utility;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;


@Component
public class LoginInterceptor implements HandlerInterceptor {
    private Logger logger = Logger.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Cookie[] cookies = httpServletRequest.getCookies();
        logger.info("cookie:" + Arrays.toString(cookies));
        if (cookies == null || cookies.length == 0) {
            System.out.println("没有Cookie");
        } else {
            System.out.println("有cookie");
            String name = "";
            String token;
            for (Cookie cookie : cookies) {
                logger.info("cookie.name:" + cookie.getName());
//                System.out.println("库克的value:"+cookie.getValue());
//                System.out.println("库克的name:"+cookie.getName());
                System.out.println(cookie.getName().equals("name"));
                if (cookie.getName().equals("name")) {
                    System.out.println("验证成功" + cookie.getValue());
                    name = cookie.getValue();
                }
                if (cookie.getName().equals("token")) {

                    token = cookie.getValue();
                    logger.info(token);
                    DESUtil desUtil = new DESUtil();
                    String str = desUtil.decrypt(token);
                    logger.info(str);
                    String name2 = str.split("\\|")[1];
                    logger.info(name2);
                    logger.info(name.equals(name2));
                    if (name.equals(name2)) {
                        httpServletRequest.getSession().setAttribute("name", name);
                        return true;
                    }
                }
            }
        }
        httpServletResponse.sendRedirect("/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

    }
}
