package com.jielim36.devgram.controller;

import com.jielim36.devgram.DTO.UserDTO;
import com.jielim36.devgram.common.ResultResponse;
import com.jielim36.devgram.enums.ResultCode;
import com.jielim36.devgram.service.FollowService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping
    public ResultResponse addFollow(Long follower_id, Long following_id) {
        boolean isSuccess = followService.addFollow(follower_id, following_id);

        if(isSuccess) {
            return ResultResponse.success(true);
        } else {
            return ResultResponse.failure(ResultCode.INTERNAL_SERVER_ERROR, "Failed to follow.");
        }
    }

    @PutMapping
    public ResultResponse unfollow(Long follower_id, Long following_id) {
        boolean unfollow = followService.unfollow(follower_id, following_id);

        if(unfollow) {
            return ResultResponse.success(true);
        } else {
            return ResultResponse.failure(ResultCode.INTERNAL_SERVER_ERROR, "Failed to unfollow.");
        }
    }

    @GetMapping("/isFollowing")
    public ResultResponse isFollowing(Long follower_id, Long following_id) {
        boolean isFollowing = followService.isFollowing(follower_id, following_id);

        return ResultResponse.success(isFollowing);
    }

    @GetMapping("/following")
    public ResultResponse getFollowingByUserId(Long follower_id, Integer pages) {
        UserDTO[] followingByUserId = followService.getFollowingByUserId(follower_id, pages);

        return ResultResponse.success(followingByUserId);
    }

    @GetMapping("/follower")
    public ResultResponse getFollowerByUserId(Long following_id, Integer pages) {
        UserDTO[] followerByUserId = followService.getFollowerByUserId(following_id, pages);

        return ResultResponse.success(followerByUserId);
    }

    @GetMapping("/following/count")
    public ResultResponse getFollowingCount(Long follower_id) {
        int followingCount = followService.getFollowingCount(follower_id);
        return ResultResponse.success(followingCount);
    }

    @GetMapping("/follower/count")
    public ResultResponse getFollowerCount(Long following_id) {
        int followerCount = followService.getFollowerCount(following_id);
        return ResultResponse.success(followerCount);
    }

}
