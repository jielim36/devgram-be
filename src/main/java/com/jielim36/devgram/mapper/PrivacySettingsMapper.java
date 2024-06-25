package com.jielim36.devgram.mapper;

import com.jielim36.devgram.entity.PrivacySettingsEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrivacySettingsMapper {

    int insertPrivacySetting(PrivacySettingsEntity privacySettingsEntity);

    PrivacySettingsEntity selectPrivacySettingByUserId(Long userId);

    int updatePrivacySettingByUserId(PrivacySettingsEntity privacySettingsEntity);
}
