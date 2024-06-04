package com.jielim36.devgram.mapper;

import com.jielim36.devgram.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {

    UserEntity selectUserByGithubId(Integer github_id);
    UserEntity selectUserByGoogleId(String google_id);

    void insertGithubUser(UserEntity user);

    void insertGoogleUser(UserEntity user);

}
