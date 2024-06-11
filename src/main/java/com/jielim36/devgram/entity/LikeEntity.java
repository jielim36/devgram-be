package com.jielim36.devgram.entity;

import com.jielim36.devgram.enums.LikeTypeEnum;
import org.springframework.stereotype.Repository;

@Repository
public class LikeEntity {

    private Long id;
    private Long parent_id;// post_id or comment_id
    private Long user_id;
    private LikeTypeEnum type;
    private Boolean is_deleted;
    private String created_at;

    public LikeEntity() {
    }

    public LikeEntity(Long id, Long parent_id, Long user_id, LikeTypeEnum type) {
        this.id = id;
        this.parent_id = parent_id;
        this.user_id = user_id;
        this.type = type;
    }

    public LikeEntity(Long id, Long parent_id, Long user_id, LikeTypeEnum type, String created_at) {
        this.id = id;
        this.parent_id = parent_id;
        this.user_id = user_id;
        this.type = type;
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LikeTypeEnum getType() {
        return type;
    }

    public void setType(LikeTypeEnum type) {
        this.type = type;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}
