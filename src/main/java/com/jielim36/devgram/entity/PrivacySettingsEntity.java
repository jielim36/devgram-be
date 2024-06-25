package com.jielim36.devgram.entity;

import com.jielim36.devgram.enums.PostVisibilityDurationEnum;
import org.springframework.stereotype.Repository;

@Repository
public class PrivacySettingsEntity {

      private Long id;
      private Long userId;
      private Boolean canSeePostFollower;
      private Boolean canSeePostFollowing;
      private Boolean canSeePostFriend;
      private PostVisibilityDurationEnum postVisibilityDurationFollower;
      private PostVisibilityDurationEnum postVisibilityDurationFollowing;
      private PostVisibilityDurationEnum postVisibilityDurationFriend;

    public PrivacySettingsEntity() {
    }

    public PrivacySettingsEntity(Long id, Long userId, Boolean canSeePostFollower, Boolean canSeePostFollowing, Boolean canSeePostFriend, PostVisibilityDurationEnum postVisibilityDurationFollower, PostVisibilityDurationEnum postVisibilityDurationFollowing, PostVisibilityDurationEnum postVisibilityDurationFriend) {
        this.id = id;
        this.userId = userId;
        this.canSeePostFollower = canSeePostFollower;
        this.canSeePostFollowing = canSeePostFollowing;
        this.canSeePostFriend = canSeePostFriend;
        this.postVisibilityDurationFollower = postVisibilityDurationFollower;
        this.postVisibilityDurationFollowing = postVisibilityDurationFollowing;
        this.postVisibilityDurationFriend = postVisibilityDurationFriend;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCanSeePostFollower() {
        return canSeePostFollower;
    }

    public void setCanSeePostFollower(Boolean canSeePostFollower) {
        this.canSeePostFollower = canSeePostFollower;
    }

    public Boolean getCanSeePostFollowing() {
        return canSeePostFollowing;
    }

    public void setCanSeePostFollowing(Boolean canSeePostFollowing) {
        this.canSeePostFollowing = canSeePostFollowing;
    }

    public Boolean getCanSeePostFriend() {
        return canSeePostFriend;
    }

    public void setCanSeePostFriend(Boolean canSeePostFriend) {
        this.canSeePostFriend = canSeePostFriend;
    }

    public PostVisibilityDurationEnum getPostVisibilityDurationFollower() {
        return postVisibilityDurationFollower;
    }

    public void setPostVisibilityDurationFollower(PostVisibilityDurationEnum postVisibilityDurationFollower) {
        this.postVisibilityDurationFollower = postVisibilityDurationFollower;
    }

    public PostVisibilityDurationEnum getPostVisibilityDurationFollowing() {
        return postVisibilityDurationFollowing;
    }

    public void setPostVisibilityDurationFollowing(PostVisibilityDurationEnum postVisibilityDurationFollowing) {
        this.postVisibilityDurationFollowing = postVisibilityDurationFollowing;
    }

    public PostVisibilityDurationEnum getPostVisibilityDurationFriend() {
        return postVisibilityDurationFriend;
    }

    public void setPostVisibilityDurationFriend(PostVisibilityDurationEnum postVisibilityDurationFriend) {
        this.postVisibilityDurationFriend = postVisibilityDurationFriend;
    }

    @Override
    public String toString() {
        return "PrivacySettingsEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", canSeePostFollower=" + canSeePostFollower +
                ", canSeePostFollowing=" + canSeePostFollowing +
                ", canSeePostFriend=" + canSeePostFriend +
                ", postVisibilityDurationFollower=" + postVisibilityDurationFollower +
                ", postVisibilityDurationFollowing=" + postVisibilityDurationFollowing +
                ", postVisibilityDurationFriend=" + postVisibilityDurationFriend +
                '}';
    }
}
