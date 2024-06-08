package com.jielim36.devgram.entity;

import org.springframework.stereotype.Repository;

@Repository
public class StoryEntity {

    private Long id;
    private Long user_id;
    private String image_url;
    private String created_at;

    public StoryEntity() {
    }

    public StoryEntity(Long id, Long user_id, String image_url, String created_at) {
        this.id = id;
        this.user_id = user_id;
        this.image_url = image_url;
        this.created_at = created_at;
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
