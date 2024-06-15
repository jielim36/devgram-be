package com.jielim36.devgram.service;

import com.jielim36.devgram.DTO.CommentDTO;
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

@Service
public class PostService {

    private final AmazonClient amazonClient;
    private final PostMapper postMapper;
    private final PostImagesMapper postImagesMapper;
    private final UserService userService;
    private final PostImagesService postImagesService;
    private final CommentService commentService;
    private final LikeService likeService;

    public PostService(AmazonClient amazonClient,
                       PostMapper postMapper,
                       PostImagesMapper postImagesMapper,
                       UserService userService,
                       PostImagesService postImagesService,
                       CommentService commentService,
                       LikeService likeService) {
        this.amazonClient = amazonClient;
        this.postMapper = postMapper;
        this.postImagesMapper = postImagesMapper;
        this.userService = userService;
        this.postImagesService = postImagesService;
        this.commentService = commentService;
        this.likeService = likeService;
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
            if (postImage.getId() == null) {
                amazonClient.deleteFileFromS3Bucket(fileUrl);
                return false;
            }
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

        CommentDTO[] commentsByPostId = commentService.getCommentsByPostId(post.getId(), user_id);

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

    public PostDTO[] getPostsByUserId(Long userId) {
        PostEntity[] postsByUserId = postMapper.getPostsByUserId(userId);
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

}
