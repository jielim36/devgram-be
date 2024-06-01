package com.jielim36.devgram.service;

import com.jielim36.devgram.entity.User;
import com.jielim36.devgram.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User selectUserById(Long id) {
        // get user
        return userMapper.selectUserById(id);
    }

}
