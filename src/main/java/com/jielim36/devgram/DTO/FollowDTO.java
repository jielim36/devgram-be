package com.jielim36.devgram.DTO;

public class FollowDTO {

    private Long userId;
    private UserDTO[] followingList;
    private UserDTO[] followerList;
    private int followingCount;
    private int followerCount;

    public FollowDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserDTO[] getFollowingList() {
        return followingList;
    }

    public void setFollowingList(UserDTO[] followingList) {
        this.followingList = followingList;
    }

    public UserDTO[] getFollowerList() {
        return followerList;
    }

    public void setFollowerList(UserDTO[] followerList) {
        this.followerList = followerList;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }
}
