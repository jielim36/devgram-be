package com.jielim36.devgram.mapper;

import com.jielim36.devgram.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {

    int addUserInfo(UserInfoEntity userInfoEntity);

    int updateUserInfo(UserInfoEntity userInfoEntity);

    UserInfoEntity selectUserInfoByUserId(Long user_id);

}
