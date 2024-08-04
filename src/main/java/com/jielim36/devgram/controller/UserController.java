package com.jielim36.devgram.controller;

import com.jielim36.devgram.CustomAnnotation.UserIdRequired.AuthAOP;
import com.jielim36.devgram.CustomAnnotation.UserIdRequired.UserIdRequired;
import com.jielim36.devgram.DTO.SearchResult;
import com.jielim36.devgram.DTO.UserDTO;
import com.jielim36.devgram.enums.ResultCode;
import com.jielim36.devgram.common.ResultResponse;
import com.jielim36.devgram.entity.UserEntity;
import com.jielim36.devgram.service.AuthService;
import com.jielim36.devgram.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@RestController()
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @UserIdRequired
    @GetMapping("/me")
    public ResultResponse<UserDTO> user(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        UserEntity user = userService.selectUserById(userId);
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

    @UserIdRequired
    @PostMapping("/avatar")
    public ResultResponse updateAvatar(@RequestParam("avatarImg") MultipartFile avatarImg, HttpServletRequest request) {
        Long user_id = (Long) request.getAttribute("userId");
        boolean isSuccess = userService.updateAvatar(user_id, avatarImg);
        return isSuccess ?
                ResultResponse.success(true) : ResultResponse.failure(ResultCode.INTERNAL_SERVER_ERROR, "Failed to update avatar");
    }


}
