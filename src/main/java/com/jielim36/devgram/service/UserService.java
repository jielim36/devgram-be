package com.jielim36.devgram.service;

import com.jielim36.devgram.common.OAuthProvider;
import com.jielim36.devgram.common.OAuthUserConvert;
import com.jielim36.devgram.entity.User;
import com.jielim36.devgram.mapper.UserMapper;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User selectUserById(Long id) {
        return userMapper.selectUserById(id);
    }

    public User selectUserByThirdParty(String providerId, OAuth2AuthenticatedPrincipal userPrincipal) {

        User user = OAuthUserConvert.convertUser(providerId, userPrincipal);

        if (providerId.equals(OAuthProvider.GITHUB.getProviderName())) {
            return selectUserByGithubId(user.getGithub_id());
        } else if (providerId.equals(OAuthProvider.GOOGLE.getProviderName())) {
            return selectUserByGoogleId(user.getGoogle_id());
        }
        return null;
    }

    public User selectUserByGithubId(Integer github_id) {
        User user = userMapper.selectUserByGithubId(github_id);
        user.setPassword(null);
        return user;
    }

    public User selectUserByGoogleId(String google_id) {
        User user = userMapper.selectUserByGoogleId(google_id);
        user.setPassword(null);
        return user;
    }

}
