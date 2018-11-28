package com.service;

import com.mapper.JobMapper;
import com.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    JobMapper jobMapper;


    @Override
    public List<Job> show() {

        return jobMapper.show();
    }
}
