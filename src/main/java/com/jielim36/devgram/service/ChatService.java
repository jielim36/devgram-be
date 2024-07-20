package com.jielim36.devgram.service;

import com.jielim36.devgram.DTO.ChatDTO;
import com.jielim36.devgram.entity.ChatEntity;
import com.jielim36.devgram.entity.MessageEntity;
import com.jielim36.devgram.mapper.ChatMapper;
import com.pusher.rest.Pusher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ChatService {

    private final ChatMapper chatMapper;
    private final Pusher pusher;

    public ChatService(ChatMapper chatMapper, Pusher pusher) {
        this.chatMapper = chatMapper;
        this.pusher = pusher;
    }

    public Long getChatRoomIdByMember(Long senderId, Long receiverId) {
        ChatEntity chatEntity = new ChatEntity(senderId, receiverId);
        Long chatRoomIdByMember = chatMapper.getChatRoomIdByMember(chatEntity);

        if(chatRoomIdByMember == null) {
            ChatEntity chatRoom = createChatRoom(senderId, receiverId);
            chatRoomIdByMember = chatRoom.getId();
        }

        return chatRoomIdByMember;
    }

    public ChatEntity createChatRoom(Long senderId, Long receiverId) {
        ChatEntity chatEntity = new ChatEntity(senderId, receiverId);
        int affectedRows = chatMapper.createChatRoom(chatEntity);
        if (affectedRows == 0) {
            throw new RuntimeException("Failed to create chat room");
        }
        return chatEntity;
    }

    public ChatDTO[] getChatRoomsByUserId(Long userId) {
        return chatMapper.getChatRoomsByUserId(userId);
    }

}
