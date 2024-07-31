package com.jielim36.devgram.mapper;

import com.jielim36.devgram.DTO.ReelDTO;
import com.jielim36.devgram.entity.ReelEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface ReelMapper {

    int insertReel(ReelEntity reelEntity);

    ReelEntity selectReelById(Long id, Long user_id);

    ReelDTO[] selectReelsByUserId(Long user_id, Long me_id, Date startDate);

    ReelDTO[] selectPopularReels(Long me_id,Integer offset, Integer limit);

    int deleteReelById(Long id);

}
