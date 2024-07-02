package com.jielim36.devgram.service;

import com.jielim36.devgram.DTO.ReelDTO;
import com.jielim36.devgram.entity.ReelEntity;
import com.jielim36.devgram.mapper.ReelMapper;
import org.springframework.stereotype.Service;

@Service
public class ReelService {

    private final ReelMapper reelMapper;
    private final LikeService likeService;

    private static final Integer LIMIT = 10;

    public ReelService(ReelMapper reelMapper,
                       LikeService likeService
    ) {
        this.reelMapper = reelMapper;
        this.likeService = likeService;
    }

    public boolean insertReel(ReelEntity reelEntity) {
        return reelMapper.insertReel(reelEntity) > 0;
    }

    public ReelEntity selectReelById(Long id, Long meId) {
        return reelMapper.selectReelById(id, meId);
    }

    public ReelDTO[] selectReelsByUserId(Long user_id, Long meId) {
        return reelMapper.selectReelsByUserId(user_id, meId);
    }

    public ReelDTO[] selectPopularReels(Long meId, Integer page) {
        Integer offset = (page - 1) * LIMIT;
        return reelMapper.selectPopularReels(meId, offset, LIMIT);
    }

    public boolean deleteReelById(Long id) {
        return reelMapper.deleteReelById(id) > 0;
    }

}
