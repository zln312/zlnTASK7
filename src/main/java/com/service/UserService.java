package com.service;

import com.model.User;

public interface UserService {

    long add(User user);

    User showOne(String name);

    User showByPhone(String phone);

    User showByPostbox(String postbox);

    void updateByName(User user);
}
