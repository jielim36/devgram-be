package com.jielim36.devgram.common.DTO;

import com.jielim36.devgram.entity.CommentEntity;

public class PostDTO {

    private Long id;
    private Long user_id;
    private String description;
    private String created_at;
    private String updated_at;
    private String[] images_url;
    private CommentEntity[] comments;
    private Integer likes;

    public PostDTO(Long id, Long user_id, String description, String created_at, String updated_at, String[] images_url, CommentEntity[] comments, Integer likes) {
        this.id = id;
        this.user_id = user_id;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.images_url = images_url;
        this.comments = comments;
        this.likes = likes;
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

    public String[] getImages_url() {
        return images_url;
    }

    public void setImages_url(String[] images_url) {
        this.images_url = images_url;
    }

    public CommentEntity[] getComments() {
        return comments;
    }

    public void setComments(CommentEntity[] comments) {
        this.comments = comments;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
