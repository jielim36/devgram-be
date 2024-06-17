package com.jielim36.devgram.controller;

import com.jielim36.devgram.DTO.UserDTO;
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
    public boolean addFollow(Long follower_id, Long following_id) {
        return followService.addFollow(follower_id, following_id);
    }

    @PutMapping
    public boolean unfollow(Long follower_id, Long following_id) {
        return followService.unfollow(follower_id, following_id);
    }

    @GetMapping("/isFollowing")
    public boolean isFollowing(Long follower_id, Long following_id) {
        return followService.isFollowing(follower_id, following_id);
    }

    @GetMapping("/following")
    public UserDTO[] getFollowingByUserId(Long follower_id, Integer pages) {
        return followService.getFollowingByUserId(follower_id, pages);
    }

    @GetMapping("/follower")
    public UserDTO[] getFollowerByUserId(Long following_id, Integer pages) {
        return followService.getFollowerByUserId(following_id, pages);
    }

    @GetMapping("/following/count")
    public int getFollowingCount(Long follower_id) {
        return followService.getFollowingCount(follower_id);
    }

    @GetMapping("/follower/count")
    public int getFollowerCount(Long following_id) {
        return followService.getFollowerCount(following_id);
    }

}
