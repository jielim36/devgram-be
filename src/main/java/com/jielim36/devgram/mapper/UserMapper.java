package com.jielim36.devgram.mapper;

import com.jielim36.devgram.DTO.UserDTO;
import com.jielim36.devgram.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserEntity[] selectUsers();

    UserEntity selectUserById(Long id);

    UserEntity selectUserByGithubId(Integer github_id);

    UserEntity selectUserByGoogleId(String google_id);

    int updateUsername(Long id, String username);

    UserDTO[] searchUser(String value, int offset, int limit, Long me_id);

    int getTotalUserNumberBySearch(String value);

    UserEntity findUserByEmail(String email);

    int uploadAvatarByUserId(Long id, String avatar_url);

}
