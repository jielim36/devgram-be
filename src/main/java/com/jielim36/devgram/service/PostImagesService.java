package com.jielim36.devgram.service;

import com.jielim36.devgram.entity.PostImageEntity;
import com.jielim36.devgram.mapper.PostImagesMapper;
import org.springframework.stereotype.Service;

@Service
public class PostImagesService {

    private final PostImagesMapper postImagesMapper;
    private final AmazonClient amazonClient;

    public PostImagesService(PostImagesMapper postImagesMapper, AmazonClient amazonClient) {
        this.postImagesMapper = postImagesMapper;
        this.amazonClient = amazonClient;
    }

    public void deletePostImagesByPostId(Long postId) {
        PostImageEntity[] postImagesByPostId = getPostImagesByPostId(postId);
        for (PostImageEntity postImage : postImagesByPostId) {
            postImagesMapper.deletePostImageByPostId(postImage.getId());
            amazonClient.deleteFileFromS3Bucket(postImage.getImage_url());
        }
    }

    public PostImageEntity[] getPostImagesByPostId(Long postId) {
        return postImagesMapper.getPostImagesByPostId(postId);
    }

}
