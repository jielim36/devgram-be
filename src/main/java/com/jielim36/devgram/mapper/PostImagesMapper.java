package com.jielim36.devgram.mapper;

import com.jielim36.devgram.entity.PostImageEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostImagesMapper {

    void addPostImage(PostImageEntity postImage);

}
