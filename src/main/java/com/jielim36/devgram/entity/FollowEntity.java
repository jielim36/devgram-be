package com.jielim36.devgram.entity;

import org.springframework.stereotype.Repository;

@Repository
public class FollowEntity {

    private Long id;
    private Long follower_id; // user who following others
    private Long following_id; // user got followed
    private boolean is_deleted;
    private String updated_at;
    private String created_at;

    public FollowEntity() {
    }

    public FollowEntity(Long follower_id, Long following_id) {
        this.follower_id = follower_id;
        this.following_id = following_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(Long follower_id) {
        this.follower_id = follower_id;
    }

    public Long getFollowing_id() {
        return following_id;
    }

    public void setFollowing_id(Long following_id) {
        this.following_id = following_id;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "FollowEntity{" +
                "id=" + id +
                ", follower_id=" + follower_id +
                ", following_id=" + following_id +
                ", is_deleted=" + is_deleted +
                ", created_at='" + created_at + '\'' +
                '}';
    }


}
