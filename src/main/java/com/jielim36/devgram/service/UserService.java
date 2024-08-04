package com.jielim36.devgram.service;

import com.jielim36.devgram.DTO.SearchResult;
import com.jielim36.devgram.DTO.UserDTO;
import com.jielim36.devgram.enums.OAuthProviderEnum;
import com.jielim36.devgram.utils.OAuthUserConvert;
import com.jielim36.devgram.entity.UserEntity;
import com.jielim36.devgram.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserInfoService userInfoService;
    private final JwtService jwtService;
    private final AmazonClient amazonClient;
    private final Integer LIMIT = 10;

    public UserService(UserMapper userMapper, UserInfoService userInfoService, JwtService jwtService, AmazonClient amazonClient) {
        this.userMapper = userMapper;
        this.userInfoService = userInfoService;
        this.jwtService = jwtService;
        this.amazonClient = amazonClient;
    }

    public UserEntity selectUserById(Long id) {
        return userMapper.selectUserById(id);
    }

    public UserEntity selectUserByThirdParty(String providerId, OAuth2AuthenticatedPrincipal userPrincipal) {

        UserEntity user = OAuthUserConvert.convertUser(providerId, userPrincipal);

        if (providerId.equals(OAuthProviderEnum.GITHUB.getProviderName())) {
            return selectUserByGithubId(user.getGithub_id());
        } else if (providerId.equals(OAuthProviderEnum.GOOGLE.getProviderName())) {
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

    public boolean updateUsername(Long id, String username) {
        int affectedRows = userMapper.updateUsername(id, username);
        return affectedRows > 0;
    }

    public SearchResult searchUsers(String value, int page, Long me_id) {
        UserDTO[] userDTOS = userMapper.searchUser(value, page, LIMIT, me_id);
        int total = userMapper.getTotalUserNumberBySearch(value);

        SearchResult<UserDTO> result = new SearchResult<>();
        result.setData(userDTOS);
        result.setTotal(total);
        result.setLimit(LIMIT);
        result.setPage(page);

        return result;
    }

    public Optional<UserEntity> findUserByEmail(String email) {
        return Optional.ofNullable(userMapper.findUserByEmail(email));
    }

    public UserEntity getUserByRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtService.extractUsername(token);
        return userMapper.findUserByEmail(email);
    }

    public boolean updateAvatar(Long user_id, MultipartFile avatarImg) {
        String fileName = "avatar_" + user_id + ".png";
        String image_url = amazonClient.uploadFile(avatarImg, fileName);
        int affectedRows = userMapper.uploadAvatarByUserId(user_id, image_url);
        return affectedRows > 0;
    }

}
