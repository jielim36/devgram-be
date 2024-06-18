package com.jielim36.devgram.mapper;

import com.jielim36.devgram.DTO.UserDTO;
import com.jielim36.devgram.entity.FollowEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowMapper {

    void addFollow(FollowEntity follow);

    int unfollow(FollowEntity follow);

    boolean isFollowing(FollowEntity follow);

    UserDTO[] getFollowingByUserId(Long follower_id, Integer offset, Integer limit, Long userId);

    UserDTO[] getFollowerByUserId(Long following_id, Integer offset, Integer limit, Long userId);

    int getFollowingCount(Long follower_id);

    int getFollowerCount(Long following_id);
}
