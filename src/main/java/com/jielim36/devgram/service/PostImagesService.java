package com.jielim36.devgram.service;

import com.jielim36.devgram.entity.PostImageEntity;
import com.jielim36.devgram.mapper.PostImagesMapper;
import org.springframework.stereotype.Service;

@Service
public class PostImagesService {

    private final PostImagesMapper postImagesMapper;

    public PostImagesService(PostImagesMapper postImagesMapper) {
        this.postImagesMapper = postImagesMapper;
    }

    public void deletePostImagesByPostId(Long postId) {
//        postImagesMapper.deletePostImagesByPostId(postId);
    }

    public PostImageEntity[] getPostImagesByPostId(Long postId) {
        return postImagesMapper.getPostImagesByPostId(postId);
    }

}
