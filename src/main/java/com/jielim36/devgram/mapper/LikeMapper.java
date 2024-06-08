package com.jielim36.devgram.mapper;

import com.jielim36.devgram.DTO.LikeDTO;
import com.jielim36.devgram.entity.LikeEntity;
import com.jielim36.devgram.enums.LikeTypeEnum;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapper {

    void addLike(LikeEntity like);

    LikeDTO[] getLikesByPostId(Long postId);

    Integer getLikesCountByParentId(Long parentId, LikeTypeEnum type);

}
