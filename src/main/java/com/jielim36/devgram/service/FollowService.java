package com.jielim36.devgram.service;

import com.jielim36.devgram.DTO.UserDTO;
import com.jielim36.devgram.entity.FollowEntity;
import com.jielim36.devgram.mapper.FollowMapper;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    private final FollowMapper followMapper;
    private final Integer pageLimit = 15;

    public FollowService(FollowMapper followMapper) {
        this.followMapper = followMapper;
    }

    /**
     * Add a follow relationship between two users
     * @param follower_id user who is following others
     * @param following_id  user got followed
     * @return true if the follow relationship is added successfully
     */
    public boolean addFollow(Long follower_id, Long following_id) {
        FollowEntity follow = new FollowEntity(follower_id, following_id);
        followMapper.addFollow(follow);

        return follow.getId() != null;
    }

    /**
     * Remove a follow relationship between two users
     * @param follower_id user who is following others
     * @param following_id  user got followed
     * @return true if the follow relationship is removed successfully
     */
    public boolean unfollow(Long follower_id, Long following_id) {
        FollowEntity follow = new FollowEntity(follower_id, following_id);
        int affectedRows = followMapper.unfollow(follow);

        return affectedRows > 0;
    }

    /**
     * Check if a user is following another user
     * @param follower_id user who is following others
     * @param following_id  user got followed
     * @return true if the user is following the other user
     */
    public boolean isFollowing(Long follower_id, Long following_id) {
        FollowEntity follow = new FollowEntity(follower_id, following_id);
        return followMapper.isFollowing(follow);
    }

    /**
     * Get the user who is following another user
     * @param follower_id user who is following others
     * @Param pages page number
     * @Param userId client user id, use for checking if the client user is following the user in the list
     * @return return following list of the user
     */
    public UserDTO[] getFollowingByUserId(Long follower_id, Integer pages, Long userId) {
        int offset = (pages - 1) * pageLimit;
        return followMapper.getFollowingByUserId(follower_id, offset, pageLimit, userId);
    }

    /**
     * Get the user who is followed by another user
     * @param following_id user got followed
     * @return return follower(fans) list of the user
     */
    public UserDTO[] getFollowerByUserId(Long following_id, Integer pages, Long userId) {
        int offset = (pages - 1) * pageLimit;
        return followMapper.getFollowerByUserId(following_id, offset, pageLimit, userId);
    }

    /**
     * Get the count of users who are following another user
     * @param follower_id user who is following others
     * @return return the count of following users
     */
    public int getFollowingCount(Long follower_id) {
        return followMapper.getFollowingCount(follower_id);
    }

    /**
     * Get the count of users who are followed by another user
     * @param following_id user got followed
     * @return return the count of follower(fans) users
     */
    public int getFollowerCount(Long following_id) {
        return followMapper.getFollowerCount(following_id);
    }

}
