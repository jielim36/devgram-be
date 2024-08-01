package com.jielim36.devgram.controller;

import com.jielim36.devgram.DTO.SearchResult;
import com.jielim36.devgram.DTO.UserDTO;
import com.jielim36.devgram.enums.ResultCode;
import com.jielim36.devgram.common.ResultResponse;
import com.jielim36.devgram.entity.UserEntity;
import com.jielim36.devgram.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResultResponse<UserDTO> user(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                                        @AuthenticationPrincipal OAuth2User oauth2User) {
        String providerId = authorizedClient.getClientRegistration().getRegistrationId();
        UserEntity user = userService.selectUserByThirdParty(providerId, oauth2User);

        if(user == null) {
            return ResultResponse.failure(ResultCode.UNAUTHORIZED, null);
        }

        return ResultResponse.success(user.convertToDTO());
    }

    @GetMapping("/{id}")
    public ResultResponse<UserDTO> selectUserById(@PathVariable Long id) {
        // get user
        UserEntity result = userService.selectUserById(id);

        return ResultResponse.success(result.convertToDTO());
    }

    @GetMapping("/search")
    public ResultResponse searchUser(@RequestParam("value") String value,
                                                @RequestParam("page") Integer page,
                                                HttpServletRequest request) {
        Long user_id = (Long) request.getAttribute("userId");
        SearchResult searchResult = userService.searchUsers(value, page, user_id);
        return ResultResponse.success(searchResult);
    }

}
