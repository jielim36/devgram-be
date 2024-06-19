package com.jielim36.devgram.controller;

import com.jielim36.devgram.CustomAnnotation.UserIdRequired.UserIdRequired;
import com.jielim36.devgram.common.ResultResponse;
import com.jielim36.devgram.entity.UserInfoEntity;
import com.jielim36.devgram.enums.ResultCode;
import com.jielim36.devgram.service.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/{userId}/info")
    public ResultResponse getUserInfo(@PathVariable Long userId) {
        UserInfoEntity userInfo = userInfoService.getUserInfo(userId);

        if(userInfo == null || userInfo.getUser_id() == null) {
            return ResultResponse.failure(ResultCode.INTERNAL_SERVER_ERROR,"User not found");
        }

        return ResultResponse.success(userInfo);
    }

}
