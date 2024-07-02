package com.jielim36.devgram.service;

import com.jielim36.devgram.DTO.PostCommentDTO;
import com.jielim36.devgram.entity.PostCommentEntity;
import com.jielim36.devgram.mapper.CommentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final CommentMapper commentMapper;
    private final LikeService likeService;

    public CommentService(CommentMapper commentMapper,
                          LikeService likeService) {
        this.commentMapper = commentMapper;
        this.likeService = likeService;
    }

    public boolean addComment(Long postId, Long userId, Long parent_id, String content) {
        PostCommentEntity comment = new PostCommentEntity(postId, parent_id, userId, content);

        commentMapper.addComment(comment);

        return comment.getId() != null;
    }

    public PostCommentDTO[] getCommentsByPostId(Long postId, Long user_id) {
        return commentMapper.getCommentsByPostId(postId, user_id);
    }

    @Transactional
    public boolean deleteCommentByCommentId(Long commentId) {
        boolean isSuccess = commentMapper.deleteCommentByCommentId(commentId);
        boolean isDeletedLikes = likeService.deleteLikesByCommentId(commentId);

        return isSuccess && isDeletedLikes;
    }

}
