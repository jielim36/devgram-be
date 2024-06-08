package com.jielim36.devgram.controller;

import com.jielim36.devgram.DTO.PostDTO;
import com.jielim36.devgram.enums.ResultCode;
import com.jielim36.devgram.common.ResultResponse;
import com.jielim36.devgram.service.AmazonClient;
import com.jielim36.devgram.service.PostService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/post")
public class PostController {

    private final AmazonClient amazonClient;
    private final PostService postService;

    public PostController(AmazonClient amazonClient, PostService postService) {
        this.amazonClient = amazonClient;
        this.postService = postService;
    }

    @GetMapping("/popular")
    public ResultResponse<PostDTO> getPopularPosts() {
        PostDTO[] popularPosts = postService.getPopularPosts();

        if(popularPosts == null || popularPosts.length == 0 || popularPosts[0] == null) {
            return ResultResponse.failure(ResultCode.NOT_FOUND, "No popular posts found.");
        }

        return ResultResponse.success(popularPosts);
    }

    @PostMapping("/{userId}")
    public ResultResponse<Boolean> post(@RequestPart(value = "files") MultipartFile[] files,
                                        @RequestPart(value = "description") String description,
                                        @PathVariable Long userId) {

        Boolean isSuccess = postService.addPost(files, description, userId);

        return ResultResponse.success(isSuccess);
    }

}
