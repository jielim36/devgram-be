package com.jielim36.devgram.controller;

import com.jielim36.devgram.common.Response.ResultCode;
import com.jielim36.devgram.common.Response.ResultResponse;
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

    @PostMapping("/{userId}")
    public ResultResponse<Boolean> post(@RequestPart(value = "files") MultipartFile[] files,
                                        @RequestPart(value = "description") String description,
                                        @PathVariable Long userId) {

        Boolean isSuccess = postService.addPost(files, description, userId);

        return ResultResponse.success(isSuccess);
    }

}
