package com.jielim36.devgram.entity;

import org.springframework.stereotype.Repository;

@Repository
public class PostImageEntity {

    private Long id;
    private Long post_id;
    private String image_url;
    private Integer position;

    public PostImageEntity() {
    }

    public PostImageEntity(Long id, Long post_id, String image_url, Integer position) {
        this.id = id;
        this.post_id = post_id;
        this.image_url = image_url;
        this.position = position;
    }

    public PostImageEntity(Long post_id, String image_url, Integer position) {
        this.post_id = post_id;
        this.image_url = image_url;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
