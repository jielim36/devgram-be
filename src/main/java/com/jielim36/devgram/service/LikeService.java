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

    public void addLike(Long postId, Long userId, LikeTypeEnum type) {
        LikeEntity like = new LikeEntity(null, postId, userId, type);
        likeMapper.addLike(like);
    }

    public LikeDTO[] getLikesByPostId(Long postId) {
        LikeDTO[] likesByPostId = likeMapper.getLikesByPostId(postId);
        return likesByPostId;
    }

}
