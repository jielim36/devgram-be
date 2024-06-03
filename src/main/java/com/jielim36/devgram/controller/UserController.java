package com.jielim36.devgram.controller;

import com.jielim36.devgram.common.Response.ResultCode;
import com.jielim36.devgram.common.Response.ResultResponse;
import com.jielim36.devgram.entity.User;
import com.jielim36.devgram.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResultResponse<User> user(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                                    @AuthenticationPrincipal OAuth2User oauth2User) {
        String providerId = authorizedClient.getClientRegistration().getRegistrationId();
        User user = userService.selectUserByThirdParty(providerId, oauth2User);

        if(user == null) {
            return ResultResponse.failure(ResultCode.UNAUTHORIZED, null);
        }

        return ResultResponse.success(user);
    }

    @GetMapping("/{id}")
    public ResultResponse<User> selectUserById(@PathVariable Long id) {
        // get user
        User result = userService.selectUserById(id);

        return ResultResponse.success(result);
    }

}
