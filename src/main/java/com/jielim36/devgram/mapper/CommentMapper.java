package com.jielim36.devgram.mapper;

import com.jielim36.devgram.DTO.CommentDTO;
import com.jielim36.devgram.entity.CommentEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    void addComment(CommentEntity commentEntity);
    CommentDTO[] getCommentsByPostId(Long postId);


}
