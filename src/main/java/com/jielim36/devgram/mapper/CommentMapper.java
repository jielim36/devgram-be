package com.jielim36.devgram.mapper;

import com.jielim36.devgram.DTO.PostCommentDTO;
import com.jielim36.devgram.entity.PostCommentEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    void addComment(PostCommentEntity postCommentEntity);
    PostCommentDTO[] getCommentsByPostId(Long post_id, Long user_id);
    boolean deleteCommentByCommentId(Long id);
    int deleteCommentsByPostId(Long post_id);

}
