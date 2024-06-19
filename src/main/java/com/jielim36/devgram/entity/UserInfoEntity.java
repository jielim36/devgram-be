package com.jielim36.devgram.entity;

public class UserInfoEntity {
    private Long id;
    private Long user_id;
    private String address;
    private String gender;
    private String bio;
    private String birthday;

    public UserInfoEntity() {
    }

    public UserInfoEntity(Long user_id, String address, String gender, String bio, String birthday) {
        this.user_id = user_id;
        this.address = address;
        this.gender = gender;
        this.bio = bio;
        this.birthday = birthday;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "UserInfoEntity{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", bio='" + bio + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
