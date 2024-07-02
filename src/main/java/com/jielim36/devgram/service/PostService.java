package com.jielim36.devgram.service;

import com.jielim36.devgram.DTO.PostCommentDTO;
import com.jielim36.devgram.DTO.LikeDTO;
import com.jielim36.devgram.DTO.PostDTO;
import com.jielim36.devgram.entity.PostEntity;
import com.jielim36.devgram.entity.PostImageEntity;
import com.jielim36.devgram.entity.UserEntity;
import com.jielim36.devgram.mapper.PostImagesMapper;
import com.jielim36.devgram.mapper.PostMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class PostService {

    private final AmazonClient amazonClient;
    private final PostMapper postMapper;
    private final PostImagesMapper postImagesMapper;
    private final UserService userService;
    private final PostImagesService postImagesService;
    private final CommentService commentService;
    private final LikeService likeService;
    private final PrivacySettingsService privacySettingsService;
    private final Integer pageLimit = 5;

    public PostService(AmazonClient amazonClient,
                       PostMapper postMapper,
                       PostImagesMapper postImagesMapper,
                       UserService userService,
                       PostImagesService postImagesService,
                       CommentService commentService,
                       LikeService likeService,
                       PrivacySettingsService privacySettingsService
                       ) {
        this.amazonClient = amazonClient;
        this.postMapper = postMapper;
        this.postImagesMapper = postImagesMapper;
        this.userService = userService;
        this.postImagesService = postImagesService;
        this.commentService = commentService;
        this.likeService = likeService;
        this.privacySettingsService = privacySettingsService;
    }

    @Transactional
    public boolean addPost(MultipartFile[] files, String description, Long userId) {
        PostEntity post = new PostEntity(userId, description);
        postMapper.addPost(post);

        // if post success added in database, it will return post with id
        if (post.getId() == null){
            throw new RuntimeException("Failed to add post");
        }

        String[] images_url = new String[files.length];

        try {
            for (int i = 0; i < files.length; i++) {
                String fileUrl = amazonClient.uploadFile(files[i], "post_" + post.getId() + "_" + i + ".jpg");
                images_url[i] = fileUrl;
                if (fileUrl == null) {
                    throw new RuntimeException("Failed to upload image");
                }

                // after added image in S3, save the img url in database
                PostImageEntity postImage = new PostImageEntity(post.getId(), fileUrl, i);
                postImagesMapper.addPostImage(postImage);
                if (postImage.getId() == null) {
                    throw new RuntimeException("Failed to add post image");
                }
            }
        } catch (Exception e) {
            for (int i = 0; i < images_url.length; i++) {
                if(images_url[i] != null){
                    amazonClient.deleteFileFromS3Bucket(images_url[i]);
                }
            }
            throw new RuntimeException("Failed to upload image");
        }

        return true;
    }

    public PostDTO getPostDTOByPostEntity(PostEntity post, Long user_id) {
        UserEntity userEntity = userService.selectUserById(post.getUser_id());
        PostImageEntity[] postImagesByPostId = postImagesService.getPostImagesByPostId(post.getId());

        String[] images_url = new String[postImagesByPostId.length];
        for (int j = 0; j < postImagesByPostId.length; j++) {
            images_url[j] = postImagesByPostId[j].getImage_url();
        }

        PostCommentDTO[] commentsByPostId = commentService.getCommentsByPostId(post.getId(), user_id);

        LikeDTO[] likesByPostId = likeService.getLikesByPostId(post.getId());

        return new PostDTO(post, userEntity.convertToDTO(), images_url, commentsByPostId, likesByPostId);
    }

    public PostDTO getPostById(Long postId, Long user_id) {
        PostEntity post = postMapper.getPostByPostId(postId);
        if (post == null) {
            return null;
        }
        return getPostDTOByPostEntity(post, user_id);
    }

    public PostDTO[] getPopularPosts(Long user_id) {
        PostEntity[] popularPosts = postMapper.getPopularPosts();
        if (popularPosts == null) {
            return null;
        }

        PostDTO[] popularPostsDTO = new PostDTO[popularPosts.length];
        for (int i = 0; i < popularPosts.length; i++) {
            popularPostsDTO[i] = getPostDTOByPostEntity(popularPosts[i],user_id);
        }

        return popularPostsDTO;
    }

    public PostDTO[] getPopularPostsWithPagination(Long user_id, Integer pages) {
        int offset = (pages - 1) * pageLimit;
        PostEntity[] popularPosts = postMapper.getPopularPostsWithPagination(offset, pageLimit);
        if (popularPosts == null) {
            return null;
        }

        PostDTO[] popularPostsDTO = new PostDTO[popularPosts.length];
        for (int i = 0; i < popularPosts.length; i++) {
            popularPostsDTO[i] = getPostDTOByPostEntity(popularPosts[i],user_id);
        }

        return popularPostsDTO;
    }

    public PostDTO[] getFollowingPosts(Long userId) {
        PostEntity[] followingPosts = postMapper.getFollowingPosts(userId);
        if (followingPosts == null) {
            return null;
        }

        PostDTO[] followingPostsDTO = new PostDTO[followingPosts.length];
        for (int i = 0; i < followingPosts.length; i++) {
            followingPostsDTO[i] = getPostDTOByPostEntity(followingPosts[i], userId);
        }

        return followingPostsDTO;
    }

    public PostDTO[] getFollowingPostsWithPagination(Long userId, Integer pages) {
        int offset = (pages - 1) * pageLimit;
        PostEntity[] followingPosts = postMapper.getFollowingPostsWithPagination(userId, offset, pageLimit);
        if (followingPosts == null) {
            return null;
        }

        PostDTO[] followingPostsDTO = new PostDTO[followingPosts.length];
        for (int i = 0; i < followingPosts.length; i++) {
            followingPostsDTO[i] = getPostDTOByPostEntity(followingPosts[i], userId);
        }

        return followingPostsDTO;
    }

    public PostDTO[] getPostsByUserId(Long userId, Long ownUserId) {
        Date postVisibilityDuration = privacySettingsService.getPostVisibilityDuration(ownUserId, userId);

        PostEntity[] postsByUserId = postMapper.getPostsByUserId(userId, postVisibilityDuration);
        if (postsByUserId == null) {
            return null;
        }

        PostDTO[] postsDTO = new PostDTO[postsByUserId.length];
        for (int i = 0; i < postsByUserId.length; i++) {
            postsDTO[i] = getPostDTOByPostEntity(postsByUserId[i], userId);
        }

        return postsDTO;
    }

    @Transactional
    public boolean deletePostByPostId(Long postId) {
        boolean isSuccess = postMapper.deletePostByPostId(postId);
        postImagesService.deletePostImagesByPostId(postId);

        return isSuccess;
    }

    public boolean updatePost(PostEntity post) {
        return postMapper.updatePost(post) > 0;
    }

}
