package com.jielim36.devgram;

import com.jielim36.devgram.DTO.*;
import com.jielim36.devgram.entity.*;
import com.jielim36.devgram.enums.LikeTypeEnum;
import com.jielim36.devgram.enums.PostVisibilityDurationEnum;
import com.jielim36.devgram.enums.ReelPlatformEnum;
import com.jielim36.devgram.mapper.*;
import com.jielim36.devgram.service.*;
import com.pusher.rest.Pusher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;

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

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private FollowService followService;

    @Autowired
    private PrivacySettingsService privacySettingsService;

    @Autowired
    private PrivacySettingsMapper privacySettingsMapper;

    @Autowired
    private ReelService reelService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private PostImagesService postImagesService;

    @Autowired
    private Pusher pusher;

    @Autowired
    private FCMService fcmService;

    @Autowired
    private AmazonClient amazonClient;

    @Autowired
    private PostImagesMapper postImagesMapper;

    private final Long userId = 1001L;

    @Test
    void contextLoads() {
    }


    @Test
    void selectProfileReels() {
        Long meId = 1002L;
        Long profileUserId = 1001L;
        ReelDTO[] reels = reelService.selectReelsByUserId(profileUserId, meId);
        System.out.println(Arrays.toString(reels));
    }

    @Test
    void searchPosts() {
        Long userId = 1001L;
        Integer page = 2;
        String value = "y";
        SearchResult<PostDTO> searchPostsWithPagination = postService.getSearchPostsWithPagination(value, page, userId);
        System.out.println(searchPostsWithPagination.getTotal());
    }

}
