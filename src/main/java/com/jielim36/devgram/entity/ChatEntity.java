package com.jielim36.devgram.entity;

import org.springframework.stereotype.Repository;

@Repository
public class ChatEntity {

    private Long id;
    private Long user1_id;
    private Long user2_id;
    private String created_at;

    public ChatEntity() {
    }

    public ChatEntity(Long user1_id, Long user2_id) {
        this.user1_id = user1_id;
        this.user2_id = user2_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser1_id() {
        return user1_id;
    }

    public void setUser1_id(Long user1_id) {
        this.user1_id = user1_id;
    }

    public Long getUser2_id() {
        return user2_id;
    }

    public void setUser2_id(Long user2_id) {
        this.user2_id = user2_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
