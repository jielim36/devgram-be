package com.jielim36.devgram.controller;

import com.jielim36.devgram.CustomAnnotation.UserIdRequired.UserIdRequired;
import com.jielim36.devgram.DTO.PostDTO;
import com.jielim36.devgram.enums.LikeTypeEnum;
import com.jielim36.devgram.enums.ResultCode;
import com.jielim36.devgram.common.ResultResponse;
import com.jielim36.devgram.service.AmazonClient;
import com.jielim36.devgram.service.LikeService;
import com.jielim36.devgram.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/post")
public class PostController {

    private final AmazonClient amazonClient;
    private final PostService postService;
    private final LikeService likeService;

    public PostController(
                AmazonClient amazonClient,
                PostService postService,
                LikeService likeService) {
        this.amazonClient = amazonClient;
        this.postService = postService;
        this.likeService = likeService;
    }

    @GetMapping("/popular")
    public ResultResponse<PostDTO> getPopularPosts() {
        PostDTO[] popularPosts = postService.getPopularPosts();

        if(popularPosts == null || popularPosts.length == 0 || popularPosts[0] == null) {
            return ResultResponse.failure(ResultCode.NOT_FOUND, "No popular posts found.");
        }

        ResultResponse success = ResultResponse.success(popularPosts);
        return success;
    }

    @PostMapping("/{userId}")
    public ResultResponse<Boolean> post(@RequestPart(value = "files") MultipartFile[] files,
                                        @RequestPart(value = "description") String description,
                                        @PathVariable Long userId) {

        Boolean isSuccess = postService.addPost(files, description, userId);

        return ResultResponse.success(isSuccess);
    }

    @UserIdRequired
    @PostMapping("/{postId}/likes")
    public ResultResponse<Boolean> likePost(@PathVariable Long postId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");

        boolean isSuccess = likeService.addLike(postId, userId, LikeTypeEnum.POST);

        return ResultResponse.success(isSuccess);
    }

    @UserIdRequired
    @DeleteMapping("/{postId}/likes")
    public ResultResponse<Boolean> unlikePost(@PathVariable Long postId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");

        boolean isSuccess = likeService.removeLike(postId, userId, LikeTypeEnum.POST);

        return ResultResponse.success(isSuccess);
    }


}
