package com.jielim36.devgram.service;

import com.jielim36.devgram.enums.OAuthProviderEnum;
import com.jielim36.devgram.utils.OAuthUserConvert;
import com.jielim36.devgram.entity.UserEntity;
import com.jielim36.devgram.mapper.AuthMapper;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthMapper authMapper;

    public AuthService(AuthMapper authMapper) {
        this.authMapper = authMapper;
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

    public void register(String providerId, OAuth2AuthenticatedPrincipal userPrincipal) {
        if (OAuthProviderEnum.GITHUB.getProviderName().equals(providerId)) {
            register_github(userPrincipal);
        } else if (OAuthProviderEnum.GOOGLE.getProviderName().equals(providerId)) {
            register_google(userPrincipal);
        }
    }

    public void register_github(OAuth2AuthenticatedPrincipal principal) {
//        User user = new User();
//        user.setGithub_id(principal.getAttribute("id"));
//        user.setUsername(principal.getAttribute("login"));
//        user.setAvatar_url(principal.getAttribute("avatar_url"));
        // GitHub account would not return email
//        user.setEmail(principal.getAttribute("email"));
        UserEntity user = OAuthUserConvert.convertGithubUser(principal);

        authMapper.insertGithubUser(user);
    }

    public void register_google(OAuth2AuthenticatedPrincipal principal) {
        UserEntity user = OAuthUserConvert.convertGoogleUser(principal);

        authMapper.insertGoogleUser(user);
    }

}
