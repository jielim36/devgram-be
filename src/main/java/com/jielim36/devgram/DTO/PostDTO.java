package com.jielim36.devgram.DTO;

import com.jielim36.devgram.entity.CommentEntity;
import com.jielim36.devgram.entity.PostEntity;

import java.util.Arrays;

public class PostDTO {

    private Long id;
    private UserDTO user;
    private String description;
    private String created_at;
    private String updated_at;
    private String[] images_url;
    private CommentDTO[] comments;
    private LikeDTO[] likes;

    public PostDTO(Long id, UserDTO user, String description, String created_at, String updated_at, String[] images_url, CommentDTO[] comments, LikeDTO[] likes) {
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
        this.created_at = postEntity.getCreated_at().toString();
        this.updated_at = postEntity.getUpdated_at().toString();
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

    @Override
    public String toString() {
        return "PostDTO{" +
                "id=" + id +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", images_url=" + Arrays.toString(images_url) +
                ", comments=" + Arrays.toString(comments) +
                ", likes=" + Arrays.toString(likes) +
                '}';
    }
}
