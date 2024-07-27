package com.jielim36.devgram.entity;

public class FirebaseUserTokenEntity {
    private Long id;
    private Long user_id;
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "FirebaseUserTokenEntity{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", token='" + token + '\'' +
                '}';
    }
}
