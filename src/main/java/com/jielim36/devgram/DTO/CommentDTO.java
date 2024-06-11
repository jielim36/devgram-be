package com.jielim36.devgram.DTO;

public class CommentDTO {

    private Long id;
    private Long post_id;
    private Long parent_id;
    private UserDTO user;
    private String content;
    private Integer like_count;
    private Boolean is_liked;
    private String created_at;
    private String updated_at;

    public CommentDTO() {
    }

    public CommentDTO(Long id, Long post_id, Long parent_id, UserDTO user, String content, Integer like_count,boolean is_liked, String created_at, String updated_at) {
        this.id = id;
        this.post_id = post_id;
        this.parent_id = parent_id;
        this.user = user;
        this.content = content;
        this.like_count = like_count;
        this.is_liked = is_liked;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
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

    public void setUser(UserDTO user_id) {
        this.user= user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getLike_count() {
        return like_count;
    }

    public void setLike_count(Integer like_count) {
        this.like_count = like_count;
    }

    public Boolean getIs_liked() {
        return is_liked;
    }

    public void setIs_liked(Boolean is_liked) {
        this.is_liked = is_liked;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", post_id=" + post_id +
                ", parent_id=" + parent_id +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", like_count=" + like_count +
                ", is_liked=" + is_liked +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
