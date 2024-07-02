package com.jielim36.devgram.DTO;

import com.jielim36.devgram.entity.ReelEntity;

public class ReelDTO {

    private Long id;
    private UserDTO user;
    private String description;
    private String reel_url;
    private String platform;
    private Integer like_count;
    private Integer comment_count;
    private Boolean is_liked;
    private String created_at;
    private String updated_at;

    public ReelDTO() {
    }

    public ReelDTO(ReelEntity reelEntity, UserDTO user, Integer like_count, Boolean isLiked) {
        this.id = reelEntity.getId();
        this.user = user;
        this.description = reelEntity.getDescription();
        this.reel_url = reelEntity.getReel_url();
        this.platform = reelEntity.getPlatform().getPlatform();
        this.like_count = like_count;
        this.is_liked = isLiked;
        this.created_at = reelEntity.getCreated_at();
        this.updated_at = reelEntity.getUpdated_at();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getLike_count() {
        return like_count;
    }

    public void setLike_count(Integer like_count) {
        this.like_count = like_count;
    }

    public Boolean getIs_liked() {
        return is_liked;
    }

    public void setIs_liked(Boolean is_liked) {
        this.is_liked = is_liked;
    }

    public Integer getComment_count() {
        return comment_count;
    }

    public void setComment_count(Integer comment_count) {
        this.comment_count = comment_count;
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
        return "ReelDTO{" +
                "id=" + id +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", reel_url='" + reel_url + '\'' +
                ", platform='" + platform + '\'' +
                ", like_count=" + like_count +
                ", is_liked=" + is_liked +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
