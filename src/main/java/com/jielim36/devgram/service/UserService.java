package com.jielim36.devgram.service;

import com.jielim36.devgram.common.OAuthProvider;
import com.jielim36.devgram.common.OAuthUserConvert;
import com.jielim36.devgram.entity.UserEntity;
import com.jielim36.devgram.mapper.UserMapper;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserEntity selectUserById(Long id) {
        return userMapper.selectUserById(id);
    }

    public UserEntity selectUserByThirdParty(String providerId, OAuth2AuthenticatedPrincipal userPrincipal) {

        UserEntity user = OAuthUserConvert.convertUser(providerId, userPrincipal);

        if (providerId.equals(OAuthProvider.GITHUB.getProviderName())) {
            return selectUserByGithubId(user.getGithub_id());
        } else if (providerId.equals(OAuthProvider.GOOGLE.getProviderName())) {
            return selectUserByGoogleId(user.getGoogle_id());
        }
        return null;
    }

    public UserEntity selectUserByGithubId(Integer github_id) {
        UserEntity user = userMapper.selectUserByGithubId(github_id);
        user.setPassword(null);
        return user;
    }

    public UserEntity selectUserByGoogleId(String google_id) {
        UserEntity user = userMapper.selectUserByGoogleId(google_id);
        user.setPassword(null);
        return user;
    }

}
