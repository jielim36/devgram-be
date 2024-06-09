package com.jielim36.devgram.controller;

import com.jielim36.devgram.CustomAnnotation.UserIdRequired.UserIdRequired;
import com.jielim36.devgram.common.ResultResponse;
import com.jielim36.devgram.enums.ResultCode;
import com.jielim36.devgram.service.CommentService;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
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

}
