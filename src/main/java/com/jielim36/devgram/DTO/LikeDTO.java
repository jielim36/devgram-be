package com.jielim36.devgram.DTO;

import com.jielim36.devgram.entity.LikeEntity;
import com.jielim36.devgram.enums.LikeTypeEnum;

public class LikeDTO {

    private Long id;
    private Long parent_id;
    private UserDTO user;
    private String type;
    private String created_at;

    public LikeDTO() {
    }

    public LikeDTO(Long id, Long parent_id, UserDTO user, LikeTypeEnum type, String created_at) {
        this.id = id;
        this.parent_id = parent_id;
        this.user = user;
        this.type = type.getType();
        this.created_at = created_at;
    }

    public LikeDTO(LikeEntity likeEntity, UserDTO user) {
        this.id = likeEntity.getId();
        this.parent_id = likeEntity.getParent_id();
        this.user = user;
        this.type = likeEntity.getType().getType();
        this.created_at = likeEntity.getCreated_at();
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "LikeDTO{" +
                "id=" + id +
                ", parent_id=" + parent_id +
                ", user=" + user +
                ", type='" + type + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
