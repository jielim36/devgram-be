package com.jielim36.devgram.service;

import com.jielim36.devgram.entity.PrivacySettingsEntity;
import com.jielim36.devgram.entity.UserInfoEntity;
import com.jielim36.devgram.enums.OAuthProviderEnum;
import com.jielim36.devgram.enums.PostVisibilityDurationEnum;
import com.jielim36.devgram.utils.OAuthUserConvert;
import com.jielim36.devgram.entity.UserEntity;
import com.jielim36.devgram.mapper.AuthMapper;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final AuthMapper authMapper;
    private final UserInfoService userInfoService;
    private final PrivacySettingsService privacySettingsService;

    public AuthService(AuthMapper authMapper,
                       UserInfoService userInfoService,
                       PrivacySettingsService privacySettingsService) {
        this.authMapper = authMapper;
        this.userInfoService = userInfoService;
        this.privacySettingsService = privacySettingsService;
    }

    public boolean checkUserExists(String providerId, OAuth2AuthenticatedPrincipal userPrincipal) {

        if (OAuthProviderEnum.GITHUB.getProviderName().equals(providerId)) {
            return checkUserExists_github(userPrincipal.getAttribute("id"));
        } else if (OAuthProviderEnum.GOOGLE.getProviderName().equals(providerId)) {
            return checkUserExists_google(userPrincipal.getAttribute("sub"));
        }

        return false;
    }

    public boolean checkUserExists_github(Integer github_id) {
        return authMapper.selectUserByGithubId(github_id) != null;
    }

    public boolean checkUserExists_google(String google_id) {
        return authMapper.selectUserByGoogleId(google_id) != null;
    }

    @Transactional
    public void register(String providerId, OAuth2AuthenticatedPrincipal userPrincipal) {
        if (OAuthProviderEnum.GITHUB.getProviderName().equals(providerId)) {
            register_github(userPrincipal);
        } else if (OAuthProviderEnum.GOOGLE.getProviderName().equals(providerId)) {
            register_google(userPrincipal);
        }
    }

    @Transactional
    public void register_github(OAuth2AuthenticatedPrincipal principal) {
        UserEntity user = OAuthUserConvert.convertGithubUser(principal);
        authMapper.insertGithubUser(user);
        initUserData(user.getId());
    }

    @Transactional
    public void register_google(OAuth2AuthenticatedPrincipal principal) {
        UserEntity user = OAuthUserConvert.convertGoogleUser(principal);
        authMapper.insertGoogleUser(user);

        initUserData(user.getId());
    }

    public Long getUserIdByProviderId(String providerId, OAuth2AuthenticatedPrincipal userPrincipal) {
        if (OAuthProviderEnum.GITHUB.getProviderName().equals(providerId)) {
            return getUserIdByGithubId((Integer) userPrincipal.getAttribute("id"));
        } else if (OAuthProviderEnum.GOOGLE.getProviderName().equals(providerId)) {
            return getUserIdByGoogleId((String) userPrincipal.getAttribute("sub"));
        }

        return null;
    }

    public Long getUserIdByGithubId(Integer github_id) {
        return authMapper.getUserIdByGithubId(github_id);
    }

    public Long getUserIdByGoogleId(String google_id) {
        return authMapper.getUserIdByGoogleId(google_id);
    }

    public void initUserData(Long userId) {
        try{
            // create user info data
            UserInfoEntity userInfo = new UserInfoEntity( userId, null, null, null, null);
            boolean isAddedUserInfo = userInfoService.addUserInfo(userInfo);
            if(!isAddedUserInfo) {
                throw new RuntimeException("Failed to initialize user info");
            }

            // create privacy settings data
            PrivacySettingsEntity privacySettings =new PrivacySettingsEntity(
                                                        null,
                                                        userId,
                                                        true,
                                                        true,
                                                        true,
                                                        PostVisibilityDurationEnum.FOREVER,
                                                        PostVisibilityDurationEnum.FOREVER,
                                                        PostVisibilityDurationEnum.FOREVER
            );
            int affectedRows = privacySettingsService.insertPrivacySetting(privacySettings);
            if (affectedRows == 0) {
                throw new RuntimeException("Failed to initialize privacy settings");
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize user data");
        }

    }

}
