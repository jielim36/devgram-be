package com.jielim36.devgram.DTO;

import com.jielim36.devgram.entity.StoryEntity;
import com.jielim36.devgram.entity.UserEntity;

import java.util.Arrays;
import java.util.Date;

public class UserDTO {

    private Long id;
    private String username;
    private Integer github_id;
    private String google_id;
    private String email;
    private String avatar_url;
    private boolean is_active;
    private Date created_at;
    private Date updated_at;
    private StoryEntity[] stories;

    public UserDTO() {
    }

    public UserDTO(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.github_id = userEntity.getGithub_id();
        this.google_id = userEntity.getGoogle_id();
        this.email = userEntity.getEmail();
        this.avatar_url = userEntity.getAvatar_url();
        this.is_active = userEntity.isIs_active();
        this.created_at = userEntity.getCreated_at();
        this.updated_at = userEntity.getUpdated_at();
    }

    public UserDTO(UserEntity userEntity, StoryEntity[] stories) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.github_id = userEntity.getGithub_id();
        this.google_id = userEntity.getGoogle_id();
        this.email = userEntity.getEmail();
        this.avatar_url = userEntity.getAvatar_url();
        this.is_active = userEntity.isIs_active();
        this.created_at = userEntity.getCreated_at();
        this.updated_at = userEntity.getUpdated_at();
        this.stories = stories;
    }

    public UserDTO(Long id, String username, Integer github_id, String google_id, String email, String avatar_url, boolean is_active, Date created_at, Date updated_at) {
        this.id = id;
        this.username = username;
        this.github_id = github_id;
        this.google_id = google_id;
        this.email = email;
        this.avatar_url = avatar_url;
        this.is_active = is_active;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGithub_id() {
        return github_id;
    }

    public void setGithub_id(Integer github_id) {
        this.github_id = github_id;
    }

    public String getGoogle_id() {
        return google_id;
    }

    public void setGoogle_id(String google_id) {
        this.google_id = google_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
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

    public StoryEntity[] getStories() {
        return stories;
    }

    public void setStories(StoryEntity[] stories) {
        this.stories = stories;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", github_id=" + github_id +
                ", google_id='" + google_id + '\'' +
                ", email='" + email + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", is_active=" + is_active +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", stories=" + Arrays.toString(stories) +
                '}';
    }
}
