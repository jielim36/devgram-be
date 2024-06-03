package com.jielim36.devgram.mapper;

import com.jielim36.devgram.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User[] selectUsers();

    User selectUserById(Long id);

    User selectUserByGithubId(Integer github_id);

    User selectUserByGoogleId(String google_id);

}
