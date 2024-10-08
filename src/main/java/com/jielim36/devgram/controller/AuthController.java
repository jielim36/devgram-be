package com.jielim36.devgram.controller;

import com.jielim36.devgram.DTO.request.AuthenticationRequest;
import com.jielim36.devgram.DTO.request.RegisterRequest;
import com.jielim36.devgram.common.ResultResponse;
import com.jielim36.devgram.service.AuthService;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    private final AuthService authService;

    AuthController (AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/secured")
    public String secured(
            @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
            @AuthenticationPrincipal OAuth2User oauth2User) {

        return "Hello, " + oauth2User.getName() + "!" + " \nYour token is " + authorizedClient.getAccessToken().getTokenValue() + "!" + " \nYour user attributes is: " + oauth2User.getAttributes() + "!"
                + "\n\n Client:" + authorizedClient.getClientRegistration().getRegistrationId();
    }

    @PostMapping("/logout")
    public ResultResponse<Boolean> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return ResultResponse.success(true);
    }

    @PostMapping("/register")
    public ResultResponse register(@RequestBody RegisterRequest registerRequest) {
        return ResultResponse.success(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResultResponse login(@RequestBody AuthenticationRequest loginRequest) {
        return ResultResponse.success(authService.login(loginRequest));
    }

}
