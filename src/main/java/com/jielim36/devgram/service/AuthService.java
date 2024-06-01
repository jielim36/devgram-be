package com.jielim36.devgram.service;

import com.jielim36.devgram.entity.User;
import com.jielim36.devgram.mapper.AuthMapper;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public enum OAuthProvider {
        GOOGLE("google"),
        GITHUB("github");

        private final String providerName;

        OAuthProvider(String providerName) {
            this.providerName = providerName;
        }

        public String getProviderName() {
            return providerName;
        }
    }

    private final AuthMapper authMapper;

    public AuthService(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    public boolean checkUserExists(String providerId, OAuth2AuthenticatedPrincipal userPrincipal) {

        if (OAuthProvider.GITHUB.providerName.equals(providerId)) {
            return checkUserExists_github(userPrincipal.getAttribute("id"));
        } else if (OAuthProvider.GOOGLE.providerName.equals(providerId)) {
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
        if (OAuthProvider.GITHUB.providerName.equals(providerId)) {
            register_github(userPrincipal);
        } else if (OAuthProvider.GOOGLE.providerName.equals(providerId)) {
            register_google(userPrincipal);
        }
    }

    public void register_github(OAuth2AuthenticatedPrincipal principal) {
        User user = new User();
        user.setGithub_id(principal.getAttribute("id"));
        user.setUsername(principal.getAttribute("login"));
        user.setAvatar_url(principal.getAttribute("avatar_url"));
        // GitHub account would not return email
//        user.setEmail(principal.getAttribute("email"));

        authMapper.insertGithubUser(user);
    }

    public void register_google(OAuth2AuthenticatedPrincipal principal) {
        User user = new User();
        user.setGoogle_id(principal.getAttribute("sub"));
        user.setUsername(principal.getAttribute("name"));
        user.setEmail(principal.getAttribute("email"));
        user.setAvatar_url(principal.getAttribute("picture"));

        authMapper.insertGoogleUser(user);
    }

}
