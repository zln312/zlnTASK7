package com.service;

import com.mapper.ExcellentMapper;
import com.model.Excellent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExcellentServiceImpl implements ExcellentService {
    @Autowired
    private ExcellentMapper excellentMapper;

    @Override
    public List<Excellent> show() {
        return excellentMapper.show();

    }

    @Override
    public long showCount(int status) {
        return excellentMapper.showNumber(status);
    }
}
