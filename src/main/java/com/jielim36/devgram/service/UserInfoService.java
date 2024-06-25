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

    public boolean addUserInfo(UserInfoEntity userInfoEntity) {
        int affectedRows = userInfoMapper.addUserInfo(userInfoEntity);

        return affectedRows > 0;
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

        UserInfoEntity userInfo = getUserInfo(userInfoEntity.getUser_id());
        if(userInfo == null) {
            addUserInfo(userInfoEntity);
        } else {
            int affectedRows = userInfoMapper.updateUserInfo(userInfoEntity);
            if(affectedRows == 0) {
                throw new RuntimeException("Failed to update user info");
            }
            return affectedRows > 0;
        }

        return false;
    }

    public UserInfoEntity getUserInfo(Long userId) {
        return userInfoMapper.selectUserInfoByUserId(userId);
    }

}
