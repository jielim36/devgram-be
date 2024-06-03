package com.jielim36.devgram.common;

import com.jielim36.devgram.entity.User;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

public class OAuthUserConvert {

    public static User convertUser(String providerId, OAuth2AuthenticatedPrincipal principal) {
        if (OAuthProvider.GITHUB.getProviderName().equals(providerId)) {
            return convertGithubUser(principal);
        } else if (OAuthProvider.GOOGLE.getProviderName().equals(providerId)) {
            return convertGoogleUser(principal);
        }

        return null;
    }

    public static User convertGithubUser(OAuth2AuthenticatedPrincipal principal) {
        User user = new User();
        user.setGithub_id(principal.getAttribute("id"));
        user.setUsername(principal.getAttribute("login"));
        user.setAvatar_url(principal.getAttribute("avatar_url"));

        return user;
    }

    public static User convertGoogleUser(OAuth2AuthenticatedPrincipal principal) {
        User user = new User();
        user.setGoogle_id(principal.getAttribute("sub"));
        user.setUsername(principal.getAttribute("name"));
        user.setEmail(principal.getAttribute("email"));
        user.setAvatar_url(principal.getAttribute("picture"));

        return user;
    }

}
