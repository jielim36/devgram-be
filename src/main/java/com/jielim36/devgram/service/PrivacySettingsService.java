package com.jielim36.devgram.service;

import com.jielim36.devgram.entity.PrivacySettingsEntity;
import com.jielim36.devgram.mapper.PrivacySettingsMapper;
import org.springframework.stereotype.Service;

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

    public boolean isAllowedToViewPost(Long userId, Long postUserId) {
        if(Objects.equals(userId, postUserId)) {
            return true;
        }

        PrivacySettingsEntity privacySettingsEntity = privacySettingsMapper.selectPrivacySettingByUserId(postUserId);
        if (privacySettingsEntity == null) {
            return true;
        }

        boolean allowFollowerView = privacySettingsEntity.getCanSeePostFollower();
        boolean allowFollowingView = privacySettingsEntity.getCanSeePostFollowing();
        boolean allowFriendView = privacySettingsEntity.getCanSeePostFriend();

        boolean isFollower = followService.isFollowing(userId, postUserId);
        boolean isFollowing = followService.isFollowing(postUserId, userId);
        boolean isFriend = isFollower && isFollowing;

        if ((allowFollowerView && isFollower) || (allowFollowingView && isFollowing) || (allowFriendView && isFriend)) {
            return true;
        }
        return false;
    }

}
