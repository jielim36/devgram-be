package com.jielim36.devgram.service;

import com.jielim36.devgram.entity.PostEntity;
import com.jielim36.devgram.entity.PostImageEntity;
import com.jielim36.devgram.mapper.PostImagesMapper;
import com.jielim36.devgram.mapper.PostMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostService {

    private final AmazonClient amazonClient;
    private final PostMapper postMapper;
    private final PostImagesMapper postImagesMapper;

    public PostService(AmazonClient amazonClient,
                       PostMapper postMapper,
                       PostImagesMapper postImagesMapper) {
        this.amazonClient = amazonClient;
        this.postMapper = postMapper;
        this.postImagesMapper = postImagesMapper;
    }

    public boolean addPost(MultipartFile[] files, String description, Long userId) {
        PostEntity post = new PostEntity(userId, description);
        postMapper.addPost(post);

        // if post success added in database, it will return post with id
        if (post == null || post.getId() == null){
            return false;
        }

        for (int i = 0; i < files.length; i++) {
            String fileUrl = amazonClient.uploadFile(files[i], "post_" + post.getId() + "_" + i + ".jpg");
            if (fileUrl == null) {
                return false;
            }

            // after added image in S3, save the img url in database
            PostImageEntity postImage = new PostImageEntity(post.getId(), fileUrl, i);
            postImagesMapper.addPostImage(postImage);
            if (postImage == null || postImage.getId() == null) {
                amazonClient.deleteFileFromS3Bucket(fileUrl);
                return false;
            }
        }

        return true;
    }

}
