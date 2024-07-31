package com.jielim36.devgram.service;

import com.jielim36.devgram.DTO.ReelDTO;
import com.jielim36.devgram.entity.ReelEntity;
import com.jielim36.devgram.mapper.ReelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReelService {

    private final ReelMapper reelMapper;
    private final LikeService likeService;
    private final PrivacySettingsService privacySettingsService;

    private static final Integer LIMIT = 10;

    public ReelService(ReelMapper reelMapper,
                       LikeService likeService,
                          PrivacySettingsService privacySettingsService
    ) {
        this.reelMapper = reelMapper;
        this.likeService = likeService;
        this.privacySettingsService = privacySettingsService;
    }

    public boolean insertReel(ReelEntity reelEntity) {
        return reelMapper.insertReel(reelEntity) > 0;
    }

    public ReelEntity selectReelById(Long id, Long meId) {
        return reelMapper.selectReelById(id, meId);
    }

    public ReelDTO[] selectReelsByUserId(Long user_id, Long meId) {
        Date postVisibilityDuration = privacySettingsService.getPostVisibilityDuration(meId, user_id);
        return reelMapper.selectReelsByUserId(user_id, meId, postVisibilityDuration);
    }

    public ReelDTO[] selectPopularReels(Long meId, Integer page) {
        Integer offset = (page - 1) * LIMIT;
        return reelMapper.selectPopularReels(meId, offset, LIMIT);
    }

    public boolean deleteReelById(Long id) {
        return reelMapper.deleteReelById(id) > 0;
    }

}
