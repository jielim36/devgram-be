package com.jielim36.devgram.entity;

import org.springframework.stereotype.Repository;

@Repository
public class PostEntity {

    private Long id;
    private Long user_id;
    private String description;
    private String created_at;
    private String updated_at;

    public PostEntity() {
    }

    public PostEntity(Long id, Long user_id, String description, String created_at, String updated_at) {
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
        return "PostEntity{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", description='" + description + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
