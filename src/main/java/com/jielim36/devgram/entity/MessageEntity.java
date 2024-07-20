package com.jielim36.devgram.entity;

public class MessageEntity {

    private Long id;
    private Long chat_id;
    private Long sender_id;
    private Long receiver_id;
    private String content;
    private Long refer_msg_id;
    private String reaction;
    private Boolean is_read;
    private String created_at;
    private String updated_at;

    public MessageEntity() {
    }

    public MessageEntity(Long sender_id, Long receiver_id, String content) {
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.content = content;
    }

    public MessageEntity(Long id, Long chat_id, Long sender_id, Long receiver_id, String content, Long refer_msg_id, String reaction, Boolean is_read, String created_at, String updated_at) {
        this.id = id;
        this.chat_id = chat_id;
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.content = content;
        this.refer_msg_id = refer_msg_id;
        this.reaction = reaction;
        this.is_read = is_read;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChat_id() {
        return chat_id;
    }

    public void setChat_id(Long chat_id) {
        this.chat_id = chat_id;
    }

    public Long getSender_id() {
        return sender_id;
    }

    public void setSender_id(Long sender_id) {
        this.sender_id = sender_id;
    }

    public Long getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(Long receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getRefer_msg_id() {
        return refer_msg_id;
    }

    public void setRefer_msg_id(Long refer_msg_id) {
        this.refer_msg_id = refer_msg_id;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public Boolean getIs_read() {
        return is_read;
    }

    public void setIs_read(Boolean is_read) {
        this.is_read = is_read;
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

    @Override
    public String toString() {
        return "MessageEntity{" +
                "id=" + id +
                ", chat_id=" + chat_id +
                ", sender_id=" + sender_id +
                ", receiver_id=" + receiver_id +
                ", content='" + content + '\'' +
                ", refer_msg_id=" + refer_msg_id +
                ", reaction='" + reaction + '\'' +
                ", is_read=" + is_read +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
