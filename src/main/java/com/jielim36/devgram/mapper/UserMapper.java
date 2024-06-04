package com.jielim36.devgram.mapper;

import com.jielim36.devgram.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserEntity[] selectUsers();

    UserEntity selectUserById(Long id);

    UserEntity selectUserByGithubId(Integer github_id);

    UserEntity selectUserByGoogleId(String google_id);

}
