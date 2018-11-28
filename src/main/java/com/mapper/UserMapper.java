package com.mapper;

import com.model.User;


public interface UserMapper {

    int add(User user);

    User selectByName(String username);

    User selectByPhone(String phone);

    User selectByPostbox(String postbox);

    Boolean update(User user);
}
