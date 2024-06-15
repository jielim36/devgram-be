package com.jielim36.devgram.controller;

import com.jielim36.devgram.CustomAnnotation.UserIdRequired.UserIdRequired;
import com.jielim36.devgram.common.ResultResponse;
import com.jielim36.devgram.enums.LikeTypeEnum;
import com.jielim36.devgram.enums.ResultCode;
import com.jielim36.devgram.service.CommentService;
import com.jielim36.devgram.service.LikeService;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final LikeService likeService;

    public CommentController(
            CommentService commentService,
            LikeService likeService) {
        this.commentService = commentService;
        this.likeService = likeService;
    }


    @UserIdRequired
    @PostMapping("/{postId}")
    public ResultResponse addComment(
            @PathVariable Long postId,
            @RequestParam Long parentId,
            @RequestParam String content,
            HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        if(userId == null) {
            return ResultResponse.failure(ResultCode.UNAUTHORIZED);
        }

        boolean result = commentService.addComment(postId, userId, parentId, content);
        if (result) {
            return ResultResponse.success(true);
        } else {
            return ResultResponse.failure(ResultCode.INTERNAL_SERVER_ERROR, "Failed to add comment");
        }
    }

    @UserIdRequired
    @PostMapping("/{commentId}/likes")
    public ResultResponse<Boolean> likeComment(@PathVariable Long commentId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");

        boolean isSuccess = likeService.addLike(commentId, userId, LikeTypeEnum.COMMENT);

        return ResultResponse.success(isSuccess);
    }

    @UserIdRequired
    @PutMapping("/{commentId}/likes")
    public ResultResponse<Boolean> unlikeComment(@PathVariable Long commentId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");

        boolean isSuccess = likeService.unlike(commentId, userId, LikeTypeEnum.COMMENT);

        return ResultResponse.success(isSuccess);
    }

    @DeleteMapping("/{commentId}")
    public ResultResponse<Boolean> deleteComment(@PathVariable Long commentId) {
        boolean isSuccess = commentService.deleteCommentByCommentId(commentId);

        return ResultResponse.success(isSuccess);
    }

}
