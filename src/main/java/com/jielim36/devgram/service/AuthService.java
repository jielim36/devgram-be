package com.jielim36.devgram.service;

import com.jielim36.devgram.DTO.request.AuthenticationRequest;
import com.jielim36.devgram.DTO.request.RegisterRequest;
import com.jielim36.devgram.DTO.response.AuthenticationResponse;
import com.jielim36.devgram.entity.PrivacySettingsEntity;
import com.jielim36.devgram.entity.UserInfoEntity;
import com.jielim36.devgram.enums.OAuthProviderEnum;
import com.jielim36.devgram.enums.PostVisibilityDurationEnum;
import com.jielim36.devgram.utils.OAuthUserConvert;
import com.jielim36.devgram.entity.UserEntity;
import com.jielim36.devgram.mapper.AuthMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final AuthMapper authMapper;
    private final UserInfoService userInfoService;
    private final PrivacySettingsService privacySettingsService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(AuthMapper authMapper,
                       UserInfoService userInfoService,
                       PrivacySettingsService privacySettingsService,
                       UserService userService,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       @Lazy AuthenticationManager authenticationManager) {
        this.authMapper = authMapper;
        this.userInfoService = userInfoService;
        this.privacySettingsService = privacySettingsService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
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


    // For local platform register
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        if(registerRequest.getUsername() == null || registerRequest.getEmail() == null || registerRequest.getPassword() == null) {
            throw new RuntimeException("Invalid request");
        }

        if(!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())){
            throw new RuntimeException("Password and confirm password do not match");
        }

        userService.findUserByEmail(registerRequest.getEmail())
                .ifPresent(user -> {
                    throw new RuntimeException("Email already exists");
                });

        String username = registerRequest.getUsername();
        String email = registerRequest.getEmail();
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        UserEntity user = new UserEntity(username, email, encodedPassword);
        authMapper.insertLocalUser(user);
        initUserData(user.getId());

        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    // For local platform login
    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserEntity user = userService.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    // For third-party register
    public void register(String providerId, OAuth2AuthenticatedPrincipal userPrincipal) {
        if (OAuthProviderEnum.GITHUB.getProviderName().equals(providerId)) {
            register_github(userPrincipal);
        } else if (OAuthProviderEnum.GOOGLE.getProviderName().equals(providerId)) {
            register_google(userPrincipal);
        }
    }

    public void register_github(OAuth2AuthenticatedPrincipal principal) {
        UserEntity user = OAuthUserConvert.convertGithubUser(principal);
        authMapper.insertGithubUser(user);
        initUserData(user.getId());
    }

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
            // create user info data
            UserInfoEntity userInfo = new UserInfoEntity( userId, null,"Other" , null, null);
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
                                                        true,
                                                        PostVisibilityDurationEnum.FOREVER,
                                                        PostVisibilityDurationEnum.FOREVER,
                                                        PostVisibilityDurationEnum.FOREVER,
                                                        PostVisibilityDurationEnum.FOREVER
            );
            int affectedRows = privacySettingsService.insertPrivacySetting(privacySettings);
            if (affectedRows == 0) {
                throw new RuntimeException("Failed to initialize privacy settings");
            }
    }

}
