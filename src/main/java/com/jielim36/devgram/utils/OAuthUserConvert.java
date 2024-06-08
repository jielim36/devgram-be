package com.jielim36.devgram.utils;

import com.jielim36.devgram.enums.OAuthProviderEnum;
import com.jielim36.devgram.entity.UserEntity;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

public class OAuthUserConvert {

    public static UserEntity convertUser(String providerId, OAuth2AuthenticatedPrincipal principal) {
        if (OAuthProviderEnum.GITHUB.getProviderName().equals(providerId)) {
            return convertGithubUser(principal);
        } else if (OAuthProviderEnum.GOOGLE.getProviderName().equals(providerId)) {
            return convertGoogleUser(principal);
        }

        return null;
    }

    public static UserEntity convertGithubUser(OAuth2AuthenticatedPrincipal principal) {
        UserEntity user = new UserEntity();
        user.setGithub_id(principal.getAttribute("id"));
        user.setUsername(principal.getAttribute("login"));
        user.setAvatar_url(principal.getAttribute("avatar_url"));

        return user;
    }

    public static UserEntity convertGoogleUser(OAuth2AuthenticatedPrincipal principal) {
        UserEntity user = new UserEntity();
        user.setGoogle_id(principal.getAttribute("sub"));
        user.setUsername(principal.getAttribute("name"));
        user.setEmail(principal.getAttribute("email"));
        user.setAvatar_url(principal.getAttribute("picture"));

        return user;
    }

}
