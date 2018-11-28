package com.service;

import com.mapper.UserMapper;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    //添加成功，返回用户ID
    public long add(User user) {
        if (user == null) {
            return -1;
        } else {
            userMapper.add(user);
            return user.getId();
        }
    }

    @Override
    public User showOne(String name) {
        return userMapper.selectByName(name);
    }

    @Override
    public User showByPhone(String phone) {
        return userMapper.selectByPhone(phone);
    }

    @Override
    public User showByPostbox(String postbox) {
        return userMapper.selectByPostbox(postbox);
    }

    @Override
    public void updateByName(User user) {
        userMapper.update(user);


    }
}