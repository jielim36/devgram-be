package com.jielim36.devgram.mapper;

import com.jielim36.devgram.entity.PostEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface PostMapper {

    void addPost(PostEntity post);

    PostEntity[] getPopularPosts();

    PostEntity[] getPopularPostsWithPagination(int offset, int limit);

    PostEntity[] getSearchPostsWithPagination(int offset, int limit, String value);

    PostEntity[] getFollowingPosts(Long user_id);

    PostEntity[] getFollowingPostsWithPagination(Long user_id, int offset, int limit);

    PostEntity getPostByPostId(Long id);

    PostEntity[] getPostsByUserId(Long user_id, Date startDate);

    boolean deletePostByPostId(Long id);

    int updatePost(PostEntity post);

    int totalPostNumberBySearch(String value);

}
