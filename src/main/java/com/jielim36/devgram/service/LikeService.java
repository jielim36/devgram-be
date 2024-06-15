package com.jielim36.devgram.service;

import com.jielim36.devgram.DTO.LikeDTO;
import com.jielim36.devgram.entity.LikeEntity;
import com.jielim36.devgram.enums.LikeTypeEnum;
import com.jielim36.devgram.mapper.LikeMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LikeService {

    private final LikeMapper likeMapper;

    public LikeService(LikeMapper likeMapper) {
        this.likeMapper = likeMapper;
    }

    public boolean addLike(Long parent_id, Long userId, LikeTypeEnum type) {
        LikeEntity like = new LikeEntity(null, parent_id, userId, type);
        likeMapper.addLike(like);

        return like.getId() != null;
    }

    public boolean unlike(Long postId, Long userId, LikeTypeEnum type) {
        LikeEntity like = new LikeEntity(null, postId, userId, type);
        int affectedRows = likeMapper.removeLike(like);

        return affectedRows > 0;
    }

    public LikeDTO[] getLikesByPostId(Long postId) {
        LikeDTO[] likesByPostId = likeMapper.getLikesByPostId(postId);
        return likesByPostId;
    }

    public boolean isLikedByUser(Long parent_id, Long userId, LikeTypeEnum type) {
        LikeEntity like = likeMapper.isLikedByUser(parent_id, userId, type);
        return like != null;
    }

    public boolean deleteLikesByCommentId(Long commentId) {
        return likeMapper.deleteLikesByCommentId(commentId);
    }
}
