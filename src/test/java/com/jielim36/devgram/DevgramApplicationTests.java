package com.jielim36.devgram;

import com.jielim36.devgram.DTO.CommentDTO;
import com.jielim36.devgram.DTO.LikeDTO;
import com.jielim36.devgram.entity.CommentEntity;
import com.jielim36.devgram.entity.LikeEntity;
import com.jielim36.devgram.enums.LikeTypeEnum;
import com.jielim36.devgram.mapper.AuthMapper;
import com.jielim36.devgram.mapper.CommentMapper;
import com.jielim36.devgram.mapper.LikeMapper;
import com.jielim36.devgram.mapper.UserMapper;
import com.jielim36.devgram.service.AuthService;
import com.jielim36.devgram.service.LikeService;
import com.jielim36.devgram.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class DevgramApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthService authService;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private PostService postService;

    @Autowired
    private LikeService likeService;

    private final Long userId = 1001L;

    @Test
    void contextLoads() {
    }

    @Test
    void testSelectUsers() {
        System.out.println(userMapper.selectUserById(1001L));
    }

    @Test
    void testSelectComments() {
        CommentDTO[] commentsByPostId = commentMapper.getCommentsByPostId(1L, userId);
        System.out.println(Arrays.toString(commentsByPostId));
    }

    @Test
    void testAddComment() {
        CommentEntity commentAddByUnitTest = new CommentEntity(1L, null, userId, "Comment add by unit test");
        commentMapper.addComment(commentAddByUnitTest);
        System.out.println(commentAddByUnitTest.getId() != null ? "Add comment success" : "Add comment failed");

    }

    @Test
    void addLike() {
        LikeEntity like = new LikeEntity(null, 1L, userId, LikeTypeEnum.POST);
        likeMapper.addLike(like);

        System.out.println(like.getId() != null ? "Add like success" : "Add like failed");
    }

    @Test
    void testGetLikesByPostId() {
        LikeDTO[] likesByPostId = likeMapper.getLikesByPostId(1L);
        System.out.println(Arrays.toString(likesByPostId));
    }

    @Test
    void testGetPopularPost() {
        System.out.println(Arrays.toString(postService.getPopularPosts(userId)));
    }

    @Test
    void getCommentByPostId() {
        CommentDTO[] commentsByPostId = commentMapper.getCommentsByPostId(1L,userId);
        System.out.println(Arrays.toString(commentsByPostId));
    }

    @Test
    void unlikePost() {
        Long postId = 1L;

        boolean isSuccess = likeService.unlike(postId, userId, LikeTypeEnum.POST);
        System.out.println(isSuccess);
    }

    @Test
    void getPostById() {
        System.out.println(postService.getPostById(1L, userId));
    }

    @Test
    void getPostByNullUserId() {
        CommentDTO[] comments = postService.getPostById(1L, 1001L).getComments();
        System.out.println(Arrays.toString(comments));
    }

    @Test
    void likeCommentByCommentId() {
        likeService.addLike(2L, userId, LikeTypeEnum.COMMENT);
    }

    @Test
    void getPostsByUserId() {
        System.out.println(Arrays.toString(postService.getPostsByUserId(userId)));
    }

    @Test
    void getUserIdByGoogleId() {
        String googleId = "108112338196828160929";
        Long userIdByGoogleId = authService.getUserIdByGoogleId(googleId);
        System.out.println(userIdByGoogleId);
    }
}
