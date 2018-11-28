package com.service;

import com.model.Excellent;

import java.util.List;


public interface ExcellentService {
    List<Excellent> show();

    long showCount(int status);
}
