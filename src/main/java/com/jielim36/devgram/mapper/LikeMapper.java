package com.jielim36.devgram.mapper;

import com.jielim36.devgram.DTO.LikeDTO;
import com.jielim36.devgram.entity.LikeEntity;
import com.jielim36.devgram.enums.LikeTypeEnum;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapper {

    void addLike(LikeEntity like);

    int removeLike(LikeEntity like);

    LikeDTO[] getLikesByPostId(Long postId);

    LikeEntity isLikedByUser(Long parent_id, Long userId, LikeTypeEnum type);

    Integer getLikesCountByParentId(Long parentId, LikeTypeEnum type);

    boolean deleteLikesByCommentId(Long commentId);

}
