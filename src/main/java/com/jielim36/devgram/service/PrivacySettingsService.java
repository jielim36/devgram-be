package com.jielim36.devgram.service;

import com.jielim36.devgram.entity.PrivacySettingsEntity;
import com.jielim36.devgram.enums.FollowRelation;
import com.jielim36.devgram.mapper.PrivacySettingsMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Service
public class PrivacySettingsService {

    private final PrivacySettingsMapper privacySettingsMapper;
    private final FollowService followService;

    public PrivacySettingsService(PrivacySettingsMapper privacySettingsMapper,
                                  FollowService followService) {
        this.privacySettingsMapper = privacySettingsMapper;
        this.followService = followService;
    }

    public int insertPrivacySetting(PrivacySettingsEntity privacySettingsEntity) {
        return privacySettingsMapper.insertPrivacySetting(privacySettingsEntity);
    }

    public PrivacySettingsEntity selectPrivacySettingByUserId(Long userId) {
        return privacySettingsMapper.selectPrivacySettingByUserId(userId);
    }

    public int updatePrivacySetting(PrivacySettingsEntity privacySettingsEntity) {
        return privacySettingsMapper.updatePrivacySettingByUserId(privacySettingsEntity);
    }

    /**
     *
     * @param userId user that want to view the profile
     * @param privacySettingsEntity privacy settings of the user that being viewed
     * @return if return null, means the user is not allowed to view the post
     */
    public FollowRelation isAllowedToViewPost(Long userId, PrivacySettingsEntity privacySettingsEntity ) {

        Long postUserId = privacySettingsEntity.getUserId();
        boolean allowFollowerView = privacySettingsEntity.getCanSeePostFollower();
        boolean allowFollowingView = privacySettingsEntity.getCanSeePostFollowing();
        boolean allowFriendView = privacySettingsEntity.getCanSeePostFriend();

        boolean isFollower = followService.isFollowing(userId, postUserId);
        boolean isFollowing = followService.isFollowing(postUserId, userId);
        boolean isFriend = isFollower && isFollowing;

        if (allowFollowerView && isFollower) {
            return FollowRelation.FOLLOWER;
        } else if (allowFollowingView && isFollowing) {
            return FollowRelation.FOLLOWING;
        } else if (allowFriendView && isFriend) {
            return FollowRelation.FRIEND;
        }

        // step here means not allowed to access
        String entitiy = isFriend ? "friend" : isFollower ? "follower" : "the user got following";
        throw new RuntimeException("Only " + entitiy + " can view this profile");

//        return null;
    }

    public Date getPostVisibilityDuration(Long userId, Long postUserId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date foreverDate = null;
        try {
            foreverDate = dateFormat.parse("1970-01-01 00:00:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if(Objects.equals(userId, postUserId)) {
            return foreverDate;
        }

        PrivacySettingsEntity privacySettingsEntity = privacySettingsMapper.selectPrivacySettingByUserId(postUserId);
        if(privacySettingsEntity == null) {
            return foreverDate;
        }

        FollowRelation followRelation = isAllowedToViewPost(userId, privacySettingsEntity);
        if(followRelation == null) {
            return null;
        }

        int visibilityTime = 0;
        switch (followRelation) {
            case FOLLOWER:
                visibilityTime = privacySettingsEntity.getPostVisibilityDurationFollower().getDuration();
                break;
            case FOLLOWING:
                visibilityTime = privacySettingsEntity.getPostVisibilityDurationFollowing().getDuration();
                break;
            case FRIEND:
                visibilityTime = privacySettingsEntity.getPostVisibilityDurationFriend().getDuration();
                break;
        }

        if(visibilityTime == 0) {
            return foreverDate;
        }

        // get current date
        Date currentDate = new Date();

        // calculate how many days ago the post can be viewed
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_YEAR, - visibilityTime);
        Date startDate = calendar.getTime();
        return startDate;
    }

}
