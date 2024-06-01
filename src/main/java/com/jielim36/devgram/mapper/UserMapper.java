package com.jielim36.devgram.mapper;

import com.jielim36.devgram.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User[] selectUsers();

    User selectUserById(Long id);

}
