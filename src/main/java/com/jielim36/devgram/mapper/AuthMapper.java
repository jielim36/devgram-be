package com.jielim36.devgram.mapper;

import com.jielim36.devgram.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {

    User selectUserByGithubId(Integer github_id);
    User selectUserByGoogleId(String google_id);

    void insertGithubUser(User user);

    void insertGoogleUser(User user);

}
