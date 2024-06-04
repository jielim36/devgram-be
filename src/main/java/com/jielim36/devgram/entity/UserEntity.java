package com.jielim36.devgram.entity;

import com.jielim36.devgram.common.DTO.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class UserEntity {

    private Long id;
    private String username;
    private Integer github_id;
    private String google_id;
    private String email;
    private String avatar_url;
    private String password;
    private boolean is_active;
    private Date created_at;
    private Date updated_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", github_id=" + github_id +
                ", google_id=" + google_id +
                ", email='" + email + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", password='" + password + '\'' +
                ", is_active=" + is_active +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }

    public UserDTO convertToDTO() {
        this.setPassword(null);
        return new UserDTO(this);
    }

}
