package com.controller;


import com.service.ExcellentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class ExcellentController {

    @Autowired
    private ExcellentServiceImpl excellentService;

    @RequestMapping(value = "/firstPage", method = RequestMethod.GET)
    public ModelAndView test(ModelAndView modelAndView) {
        List list = excellentService.show();
        //查询已经结业的学员人数
        long count1 = excellentService.showCount(1);
        //查询在学的学员人数
        long count2 = excellentService.showCount(0);
        modelAndView.addObject("list", list);
        modelAndView.addObject("count", count2);
        modelAndView.addObject("counted", count1);
        modelAndView.setViewName("firstPage");
        return modelAndView;
    }
}
