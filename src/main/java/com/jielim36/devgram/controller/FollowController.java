package com.jielim36.devgram.controller;

import com.jielim36.devgram.CustomAnnotation.UserIdRequired.UserIdRequired;
import com.jielim36.devgram.DTO.UserDTO;
import com.jielim36.devgram.common.ResultResponse;
import com.jielim36.devgram.entity.FollowEntity;
import com.jielim36.devgram.enums.ResultCode;
import com.jielim36.devgram.service.FollowService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
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
    public ResultResponse addFollow(@RequestBody FollowEntity follow){
        Long follower_id = follow.getFollower_id();
        Long following_id = follow.getFollowing_id();
        boolean isSuccess = followService.addFollow(follower_id, following_id);

        if(isSuccess) {
            return ResultResponse.success(true);
        } else {
            return ResultResponse.failure(ResultCode.INTERNAL_SERVER_ERROR, "Failed to follow.");
        }
    }

    @PutMapping
    public ResultResponse unfollow(@RequestBody FollowEntity follow) {
        Long follower_id = follow.getFollower_id();
        Long following_id = follow.getFollowing_id();
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

    @UserIdRequired
    @GetMapping("/{follower_id}/following")
    public ResultResponse getFollowingByUserId(@PathVariable("follower_id")Long follower_id,
                                               @Param("pages") Integer pages,
                                               HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        UserDTO[] followingByUserId = followService.getFollowingByUserId(follower_id, pages, userId);

        return ResultResponse.success(followingByUserId);
    }

    @UserIdRequired
    @GetMapping("/{following_id}/follower")
    public ResultResponse getFollowerByUserId(@PathVariable("following_id") Long following_id,
                                              @Param("pages") Integer pages,
                                              HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        UserDTO[] followerByUserId = followService.getFollowerByUserId(following_id, pages, userId);

        return ResultResponse.success(followerByUserId);
    }

    @GetMapping("/{follower_id}/following/count")
    public ResultResponse getFollowingCount(@PathVariable("follower_id") Long follower_id) {
        int followingCount = followService.getFollowingCount(follower_id);
        return ResultResponse.success(followingCount);
    }

    @GetMapping("/{following_id}/follower/count")
    public ResultResponse getFollowerCount(@PathVariable("following_id") Long following_id) {
        int followerCount = followService.getFollowerCount(following_id);
        return ResultResponse.success(followerCount);
    }

}
