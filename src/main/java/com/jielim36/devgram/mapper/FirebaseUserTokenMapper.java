package com.jielim36.devgram.mapper;

import com.jielim36.devgram.entity.FirebaseUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FirebaseUserTokenMapper {

    int saveToken(FirebaseUserTokenEntity userToken);

    FirebaseUserTokenEntity getTokenByUserId(Long userId);

    int updateToken(FirebaseUserTokenEntity userToken);

}
