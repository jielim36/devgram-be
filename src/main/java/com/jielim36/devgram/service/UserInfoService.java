package com.jielim36.devgram.service;

import com.jielim36.devgram.entity.UserInfoEntity;
import com.jielim36.devgram.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    private final UserInfoMapper userInfoMapper;

    public UserInfoService(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    public void addUserInfo(UserInfoEntity userInfoEntity) {
        userInfoMapper.addUserInfo(userInfoEntity);
    }

    public void updateUserInfo(UserInfoEntity userInfoEntity) {
        userInfoMapper.updateUserInfo(userInfoEntity);
    }

    public UserInfoEntity getUserInfo(Long userId) {
        return userInfoMapper.selectUserInfoByUserId(userId);
    }

}
