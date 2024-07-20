package com.jielim36.devgram.controller;

import com.jielim36.devgram.CustomAnnotation.UserIdRequired.UserIdRequired;
import com.jielim36.devgram.common.ResultResponse;
import com.jielim36.devgram.entity.ChatEntity;
import com.jielim36.devgram.entity.MessageEntity;
import com.jielim36.devgram.enums.ResultCode;
import com.jielim36.devgram.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResultResponse sendMessage(@RequestBody MessageEntity messageEntity) {
        MessageEntity message = messageService.sendMessage(messageEntity);
        return ResultResponse.success(message);
    }

    @GetMapping("/init")
    public ResultResponse getMessages(@RequestParam("user1_id") Long user1_id,
                                      @RequestParam("user2_id") Long user2_id) {
        ChatEntity chatEntity = new ChatEntity(user1_id, user2_id);
        List<MessageEntity> messages = messageService.getMessages(chatEntity);
        return ResultResponse.success(messages);
    }
}
