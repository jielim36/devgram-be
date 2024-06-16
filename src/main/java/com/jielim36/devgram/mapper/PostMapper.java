package com.jielim36.devgram.mapper;

import com.jielim36.devgram.entity.PostEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {

    void addPost(PostEntity post);

    PostEntity[] getPopularPosts();

    PostEntity getPostByPostId(Long id);

    PostEntity[] getPostsByUserId(Long user_id);

    boolean deletePostByPostId(Long id);

    int updatePost(PostEntity post);

}
