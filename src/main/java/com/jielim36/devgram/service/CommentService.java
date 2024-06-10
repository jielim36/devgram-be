package com.jielim36.devgram.service;

import com.jielim36.devgram.DTO.CommentDTO;
import com.jielim36.devgram.entity.CommentEntity;
import com.jielim36.devgram.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CommentService {

    private final CommentMapper commentMapper;

    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public boolean addComment(Long postId, Long userId, Long parent_id, String content) {
        CommentEntity comment = new CommentEntity(postId, parent_id, userId, content);

        commentMapper.addComment(comment);

        return comment.getId() != null;
    }

    public CommentDTO[] getCommentsByPostId(Long postId) {
        return commentMapper.getCommentsByPostId(postId);
    }

}
