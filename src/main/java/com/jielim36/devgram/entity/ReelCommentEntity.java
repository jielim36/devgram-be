package com.jielim36.devgram.entity;

import org.springframework.stereotype.Repository;

@Repository
public class ReelCommentEntity {

    private Long id;
    private Long reel_id;
    private Long parent_id; // if it is a reply to another comment
    private Long user_id;
    private String content;
    private String created_at;
    private String updated_at;

    public ReelCommentEntity() {
    }

    public ReelCommentEntity(Long id, Long reel_id, Long parent_id, Long user_id, String content, String created_at, String updated_at) {
        this.id = id;
        this.reel_id = reel_id;
        this.parent_id = parent_id;
        this.user_id = user_id;
        this.content = content;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public ReelCommentEntity(Long id, Long post_id, Long parent_id, Long user_id, String content) {
        this.id = id;
        this.reel_id = post_id;
        this.parent_id = parent_id;
        this.user_id = user_id;
        this.content = content;
    }

    public ReelCommentEntity(Long post_id, Long parent_id, Long user_id, String content) {
        this.reel_id = post_id;
        this.parent_id = parent_id;
        this.user_id = user_id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReel_id() {
        return reel_id;
    }

    public void setReel_id(Long reel_id) {
        this.reel_id = reel_id;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
