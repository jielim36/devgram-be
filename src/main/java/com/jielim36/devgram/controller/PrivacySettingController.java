package com.jielim36.devgram.controller;

import com.jielim36.devgram.common.ResultResponse;
import com.jielim36.devgram.entity.PrivacySettingsEntity;
import com.jielim36.devgram.enums.ResultCode;
import com.jielim36.devgram.service.PrivacySettingsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/settings/privacy")
public class PrivacySettingController {

    private final PrivacySettingsService privacySettingsService;

    public PrivacySettingController(PrivacySettingsService privacySettingsService) {
        this.privacySettingsService = privacySettingsService;
    }

    @GetMapping("/{userId}")
    public ResultResponse getPrivacySettings(@PathVariable Long userId) {
        PrivacySettingsEntity privacySettingsEntity = privacySettingsService.selectPrivacySettingByUserId(userId);

        return privacySettingsEntity.getId() != null ? ResultResponse.success(privacySettingsEntity) : ResultResponse.failure(ResultCode.INTERNAL_SERVER_ERROR,"Privacy settings not found");
    }

    @PutMapping("/{userId}")
    public ResultResponse updatePrivacySettings(@PathVariable Long userId, @RequestBody PrivacySettingsEntity privacySettingsEntity) {
        privacySettingsEntity.setUserId(userId);
        int result = privacySettingsService.updatePrivacySetting(privacySettingsEntity);

        return result > 0 ? ResultResponse.success(true) : ResultResponse.failure(ResultCode.INTERNAL_SERVER_ERROR,"Failed to update privacy settings");
    }

}
