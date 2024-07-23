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

    @UserIdRequired
    @GetMapping("/init")
    public ResultResponse getMessages(@RequestParam("user1_id") Long user1_id,
                                      @RequestParam("user2_id") Long user2_id,
                                      HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        ChatEntity chatEntity = new ChatEntity(user1_id, user2_id);
        List<MessageEntity> messages = messageService.getMessages(chatEntity, userId);
        return ResultResponse.success(messages);
    }

    @UserIdRequired
    @PutMapping("/read/{chat_id}/receiver/{receiver_id}")
    public ResultResponse updateIsReadByChatId(@PathVariable Long chat_id, @PathVariable Long receiver_id ,HttpServletRequest request) {
        Long meId = (Long) request.getAttribute("userId");
        messageService.updateIsReadByChat(chat_id, meId, receiver_id);
        return ResultResponse.success(true);
    }

    @DeleteMapping
    public ResultResponse deleteMessage(@RequestBody MessageEntity messageEntity) {
        boolean isSuccess = messageService.deleteMessage(messageEntity);
        return isSuccess ? ResultResponse.success(true) : ResultResponse.failure(ResultCode.INTERNAL_SERVER_ERROR, null);
    }

}
