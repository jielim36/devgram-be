package com.jielim36.devgram.DTO;

import com.jielim36.devgram.entity.MessageEntity;

public class ChatDTO {
    private Long id;
    private UserDTO user1;
    private UserDTO user2;
    private String created_at;
    private MessageEntity latestMessage;
    private int unread_count;

    public ChatDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser1() {
        return user1;
    }

    public void setUser1(UserDTO user1) {
        this.user1 = user1;
    }

    public UserDTO getUser2() {
        return user2;
    }

    public void setUser2(UserDTO user2) {
        this.user2 = user2;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public MessageEntity getLatestMessage() {
        return latestMessage;
    }

    public void setLatestMessage(MessageEntity latestMessage) {
        this.latestMessage = latestMessage;
    }

    public int getUnread_count() {
        return unread_count;
    }

    public void setUnread_count(int unread_count) {
        this.unread_count = unread_count;
    }

    @Override
    public String toString() {
        return "ChatDTO{" +
                "\nid=" + id +
                ", \nuser1=" + user1 +
                ", \nuser2=" + user2 +
                ", \ncreated_at='" + created_at + '\'' +
                ", \nlatestMessage=" + latestMessage +
                ", \nunread_count=" + unread_count +
                "\n}";
    }
}
