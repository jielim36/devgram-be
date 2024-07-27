package com.jielim36.devgram;

import com.jielim36.devgram.DTO.ChatDTO;
import com.jielim36.devgram.DTO.PostCommentDTO;
import com.jielim36.devgram.DTO.LikeDTO;
import com.jielim36.devgram.DTO.ReelDTO;
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
    private Pusher pusher;

    @Autowired
    private FCMService fcmService;

    private final Long userId = 1001L;

    @Test
    void contextLoads() {
    }

    @Test
    void testUpdateRead() {
        Long chatId = 1L;
        Long senderId = 1002L;
        Long receiverId = 1001L;
        String channelName = "chat." + receiverId;
        String eventName = "read";
        pusher.trigger(channelName, eventName, chatId);
    }

    @Test
    void testPushNotification() {
        Long userId = 1001L;
        String sender_avatar_url = "https://i.pinimg.com/564x/73/99/de/7399de1107c7f8cd95591f3755c1e07a.jpg";
        String title = "New Message come from Jielim36";
        String body = "Hello, I am Jielim36";

        fcmService.sendChatNotificationToUser(sender_avatar_url, userId, title, body);
    }

}
