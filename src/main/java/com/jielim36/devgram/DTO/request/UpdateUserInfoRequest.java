package com.jielim36.devgram.DTO.request;

import com.jielim36.devgram.entity.UserInfoEntity;

public class UpdateUserInfoRequest {
    private String username;
    private UserInfoEntity userInfoEntity;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserInfoEntity getUserInfoEntity() {
        return userInfoEntity;
    }

    public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
        this.userInfoEntity = userInfoEntity;
    }

    @Override
    public String toString() {
        return "UpdateUserInfoRequest{" +
                "username='" + username + '\'' +
                ", userInfoEntity=" + userInfoEntity +
                '}';
    }
}
