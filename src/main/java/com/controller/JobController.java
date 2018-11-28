package com.controller;


import com.model.Job;
import com.service.JobServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class JobController {

    @Autowired
    private JobServiceImpl jobService;
    private Logger logger = Logger.getLogger(UserController.class);

    @RequestMapping(value = "/u/thirdPage", method = RequestMethod.GET)
    public ModelAndView test3(ModelAndView modelAndView) {
        List list = jobService.show();
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        List list3 = new ArrayList();


        for (Object aList : list) {
            Job job = (Job) aList;
            if (job.getStatus().equals("前端开发方向")) {

                list1.add(job);
            }
            if (job.getStatus().equals("后端开发方向")) {

                list2.add(job);
            }
            if (job.getStatus().equals("运维方向")) {

                list3.add(job);
            }
        }
        long update = System.currentTimeMillis();
        logger.info(list1);

        modelAndView.addObject("job1", list1);
        modelAndView.addObject("job2", list2);
        modelAndView.addObject("job3", list3);
        modelAndView.addObject("time", update);
        modelAndView.setViewName("thirdPage");
        return modelAndView;

    }
}
