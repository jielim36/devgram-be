package com.jielim36.devgram.service;

import com.jielim36.devgram.entity.PrivacySettingsEntity;
import com.jielim36.devgram.mapper.PrivacySettingsMapper;
import org.springframework.stereotype.Service;

@Service
public class PrivacySettingsService {

    private final PrivacySettingsMapper privacySettingsMapper;

    public PrivacySettingsService(PrivacySettingsMapper privacySettingsMapper) {
        this.privacySettingsMapper = privacySettingsMapper;
    }

    public int insertPrivacySetting(PrivacySettingsEntity privacySettingsEntity) {
        return privacySettingsMapper.insertPrivacySetting(privacySettingsEntity);
    }

    public PrivacySettingsEntity selectPrivacySettingByUserId(Long userId) {
        return privacySettingsMapper.selectPrivacySettingByUserId(userId);
    }

    public int updatePrivacySetting(PrivacySettingsEntity privacySettingsEntity) {
        return privacySettingsMapper.updatePrivacySettingByUserId(privacySettingsEntity);
    }
}
