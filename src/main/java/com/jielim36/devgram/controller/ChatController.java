package com.jielim36.devgram.controller;

import com.jielim36.devgram.CustomAnnotation.UserIdRequired.UserIdRequired;
import com.jielim36.devgram.DTO.ChatDTO;
import com.jielim36.devgram.common.ResultResponse;
import com.jielim36.devgram.entity.ChatEntity;
import com.jielim36.devgram.service.ChatService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @UserIdRequired
    @GetMapping
    public ResultResponse getChatRooms(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        ChatDTO[] chatRoomsByUserId = chatService.getChatRoomsByUserId(userId);
        return ResultResponse.success(chatRoomsByUserId);
    }

}
