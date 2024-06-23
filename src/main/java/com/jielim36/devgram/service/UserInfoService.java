package com.jielim36.devgram.service;

import com.jielim36.devgram.DTO.request.UpdateUserInfoRequest;
import com.jielim36.devgram.entity.UserInfoEntity;
import com.jielim36.devgram.mapper.UserInfoMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserInfoService {

    private final UserInfoMapper userInfoMapper;
    private final UserService userService;

    public UserInfoService(UserInfoMapper userInfoMapper,@Lazy UserService userService) {
        this.userInfoMapper = userInfoMapper;
        this.userService = userService;
    }

    public void addUserInfo(UserInfoEntity userInfoEntity) {
        userInfoMapper.addUserInfo(userInfoEntity);
    }

    @Transactional
    public boolean updateUserInfo(UpdateUserInfoRequest userInfoRequest) {
        UserInfoEntity userInfoEntity = userInfoRequest.getUserInfoEntity();
        if(userInfoRequest.getUsername() != null) {
            boolean b = userService.updateUsername(userInfoEntity.getUser_id(), userInfoRequest.getUsername());
            if(!b) {
                throw new RuntimeException("Failed to update username");
            }
        }

        int affectedRows = userInfoMapper.updateUserInfo(userInfoEntity);
        if(affectedRows == 0) {
            throw new RuntimeException("Failed to update user info");
        }

        return affectedRows > 0;
    }

    public UserInfoEntity getUserInfo(Long userId) {
        return userInfoMapper.selectUserInfoByUserId(userId);
    }

}
