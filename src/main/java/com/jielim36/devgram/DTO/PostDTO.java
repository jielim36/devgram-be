package com.jielim36.devgram.DTO;

import com.jielim36.devgram.entity.CommentEntity;
import com.jielim36.devgram.entity.PostEntity;

import java.util.Date;

public class PostDTO {

    private Long id;
    private UserDTO user;
    private String description;
    private Date created_at;
    private Date updated_at;
    private String[] images_url;
    private CommentDTO[] comments;
    private LikeDTO[] likes;

    public PostDTO(Long id, UserDTO user, String description, Date created_at, Date updated_at, String[] images_url, CommentDTO[] comments, LikeDTO[] likes) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.images_url = images_url;
        this.comments = comments;
        this.likes = likes;
    }

    public PostDTO(PostEntity postEntity, UserDTO user, String[] images_url, CommentDTO[] comments, LikeDTO[] likes) {
        this.id = postEntity.getId();
        this.user = user;
        this.description = postEntity.getDescription();
        this.created_at = postEntity.getCreated_at();
        this.updated_at = postEntity.getUpdated_at();
        this.comments = comments;
        this.images_url = images_url;
        this.likes = likes;
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

    public String[] getImages_url() {
        return images_url;
    }

    public void setImages_url(String[] images_url) {
        this.images_url = images_url;
    }

    public CommentDTO[] getComments() {
        return comments;
    }

    public void setComments(CommentDTO[] comments) {
        this.comments = comments;
    }

    public LikeDTO[] getLikes() {
        return likes;
    }

    public void setLikes(LikeDTO[] likes) {
        this.likes = likes;
    }
}
