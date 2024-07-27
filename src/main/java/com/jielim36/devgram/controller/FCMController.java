package com.jielim36.devgram.controller;

import com.jielim36.devgram.common.ResultResponse;
import com.jielim36.devgram.entity.FirebaseNotificationRequest;
import com.jielim36.devgram.entity.FirebaseNotificationResponse;
import com.jielim36.devgram.entity.FirebaseUserTokenEntity;
import com.jielim36.devgram.service.FCMService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/fcm")
public class FCMController {

    private final FCMService fcmService;

    public FCMController(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    @PostMapping("/notification/message")
    public ResultResponse sendNotification(@RequestBody FirebaseNotificationRequest request) throws ExecutionException, InterruptedException {
        Long userId = 1001L;
        String sender_avatar_url = "https://i.pinimg.com/564x/73/99/de/7399de1107c7f8cd95591f3755c1e07a.jpg";
        String title = "New Message come from Jielim36";
        String body = "Hello, I am Jielim36";

        fcmService.sendChatNotificationToUser(sender_avatar_url, userId, title, body);
        return ResultResponse.success(true);
    }

    @PostMapping("/token")
    public ResultResponse saveToken(@RequestBody FirebaseUserTokenEntity userToken) {
        fcmService.saveToken(userToken);
        return ResultResponse.success(true);
    }

}
