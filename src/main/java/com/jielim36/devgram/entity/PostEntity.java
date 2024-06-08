package com.jielim36.devgram.entity;

import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class PostEntity {

    private Long id;
    private Long user_id;
    private String description;
    private Date created_at;
    private Date updated_at;

    public PostEntity() {
    }

    public PostEntity(Long id, Long user_id, String description, Date created_at, Date updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public PostEntity(Long id, Long user_id, String description) {
        this.id = id;
        this.user_id = user_id;
        this.description = description;
    }

    public PostEntity(Long user_id, String description) {
        this.user_id = user_id;
        this.description = description;
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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
