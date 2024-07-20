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
        PostCommentDTO[] commentsByPostId = commentMapper.getCommentsByPostId(1L, userId);
        System.out.println(Arrays.toString(commentsByPostId));
    }

    @Test
    void testAddComment() {
        PostCommentEntity commentAddByUnitTest = new PostCommentEntity(1L, null, userId, "Comment add by unit test");
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
        PostCommentDTO[] commentsByPostId = commentMapper.getCommentsByPostId(1L,userId);
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
        PostCommentDTO[] comments = postService.getPostById(1L, 1001L).getComments();
        System.out.println(Arrays.toString(comments));
    }

    @Test
    void likeCommentByCommentId() {
        likeService.addLike(2L, userId, LikeTypeEnum.COMMENT);
    }

    @Test
    void getPostsByUserId() {
//        System.out.println(Arrays.toString(postService.getPostsByUserId(userId)));
    }

    @Test
    void getUserIdByGoogleId() {
        String googleId = "108112338196828160929";
        Long userIdByGoogleId = authService.getUserIdByGoogleId(googleId);
        System.out.println(userIdByGoogleId);
    }

    @Test
    void deletePostByPostId() {
        System.out.println(postService.deletePostByPostId(11L));
    }

    @Test
    void getUserInfoByUserId() {
        UserInfoEntity userInfo = userInfoService.getUserInfo(userId);
        System.out.println(userInfo);
    }

    @Test
    void updateUserInfo() {
        String newBio = "Hi, my name is Lim Yee Jie!\nThis is my new bio! \n\n\n" +
                "This is my new youtube link: https://www.youtube.com/";

        UserInfoEntity userInfo = new UserInfoEntity();
        userInfo.setUser_id(userId);
        userInfo.setBio(newBio);

//        userInfoService.updateUserInfo(userInfo);
    }

    @Test
    void testAddPrivacySetting() {
        PrivacySettingsEntity privacySettings =new PrivacySettingsEntity(
                null,
                1002L,
                true,
                true,
                true,
                true,
                PostVisibilityDurationEnum.FOREVER,
                PostVisibilityDurationEnum.FOREVER,
                PostVisibilityDurationEnum.FOREVER,
                PostVisibilityDurationEnum.FOREVER
        );
        int isSuccess = privacySettingsService.insertPrivacySetting(privacySettings);
        System.out.println(isSuccess);
    }

    @Test
    void testSelectPrivacySettingByUserId() {
        PrivacySettingsEntity privacySettingsEntity = privacySettingsService.selectPrivacySettingByUserId(userId);
        System.out.println(privacySettingsEntity);
    }

    @Test
    void testUpdatePrivacySetting() {
        PrivacySettingsEntity privacySettings =new PrivacySettingsEntity(
                null,
                userId,
                false,
                false,
                true,
                true,
                PostVisibilityDurationEnum.FOREVER,
                PostVisibilityDurationEnum.THIRTY_DAYS,
                PostVisibilityDurationEnum.ONE_DAY,
                PostVisibilityDurationEnum.FOREVER
        );
        int isSuccess = privacySettingsService.updatePrivacySetting(privacySettings);
        System.out.println(isSuccess);
    }

    @Test
    void testIsAllowedViewProfile() {
        Long sender = userId;
        Long targetUserId = 1002L;

        Date postVisibilityDuration = privacySettingsService.getPostVisibilityDuration(sender, targetUserId);
        System.out.println(postVisibilityDuration);
    }

    @Test
    void testDate() {
        Long sender = userId;
        Long targetUserId = 1002L;
        Date postVisibilityDuration = privacySettingsService.getPostVisibilityDuration(sender, targetUserId);
        System.out.println(postVisibilityDuration);
    }

    @Test
    void testInsertReel(){
        ReelEntity reelEntity = new ReelEntity(
                null,
                userId,
                "This is a youtube video",
                "https://www.youtube.com/shorts/otI8kIrE5YA",
                ReelPlatformEnum.YOUTUBE,
                null,
                null
        );
        reelService.insertReel(reelEntity);
    }

    @Test
    void testSelectReelByUserId(){
        ReelDTO[] reelEntities = reelService.selectPopularReels(userId, 0);
        System.out.println(Arrays.toString(reelEntities));
    }

    @Test
    void testSelectReel() {
        ReelDTO[] reelDTOS = reelService.selectReelsByUserId(userId, userId);
        System.out.println(Arrays.toString(reelDTOS));
    }

    @Test
    void getMessageByUserId() {
        Long user2 = 1002L;
        ChatEntity ChatEntity = new ChatEntity(userId, user2);
        System.out.println(messageService.getMessages(ChatEntity));
    }

    @Test
    void sendMessageWithoutSaveDatabase() {
        Long sender = 1003L;
        Long receiver = 1001L;

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setSender_id(sender);
        messageEntity.setReceiver_id(receiver);
        messageEntity.setContent("Hello, this is a test message");
        messageEntity.setId(102L);

        String channelName = "chat." + messageEntity.getReceiver_id();
        String eventName = "incoming-msg";
        pusher.trigger(channelName, eventName, messageEntity);
    }

    @Test
    void sendMessageWithSaveDatabase() {
        Long sender = 1003L;
        Long receiver = 1001L;

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setSender_id(sender);
        messageEntity.setReceiver_id(receiver);
        messageEntity.setContent("Hello, this is a test message");
        messageEntity.setIs_read(false);

        messageService.sendMessage(messageEntity);
    }

    @Test
    void testGetChatRoomsByUserId() {
        ChatDTO[] chatRoomsByUserId = chatService.getChatRoomsByUserId(userId);
        System.out.println(Arrays.toString(chatRoomsByUserId));
    }
}
