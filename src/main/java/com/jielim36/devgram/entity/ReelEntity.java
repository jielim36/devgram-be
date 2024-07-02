package com.jielim36.devgram.entity;

import com.jielim36.devgram.enums.ReelPlatformEnum;
import org.springframework.stereotype.Repository;

@Repository
public class ReelEntity {

    private Long id;
    private Long user_id;
    private String description;
    private String reel_url;
    private ReelPlatformEnum platform;
    private String created_at;
    private String updated_at;

    public ReelEntity() {
    }

    public ReelEntity(Long id, Long user_id, String description, String reel_url, ReelPlatformEnum platform, String created_at, String updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.description = description;
        this.reel_url = reel_url;
        this.platform = platform;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReel_url() {
        return reel_url;
    }

    public void setReel_url(String reel_url) {
        this.reel_url = reel_url;
    }

    public ReelPlatformEnum getPlatform() {
        return platform;
    }

    public void setPlatform(ReelPlatformEnum platform) {
        this.platform = platform;
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
        return "ReelEntity{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", description='" + description + '\'' +
                ", reel_url='" + reel_url + '\'' +
                ", platform=" + platform +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
