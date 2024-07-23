package com.jielim36.devgram.service;

import com.jielim36.devgram.entity.ChatEntity;
import com.jielim36.devgram.entity.MessageEntity;
import com.jielim36.devgram.mapper.MessageMapper;
import com.pusher.rest.Pusher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final ChatService chatService;
    private final MessageMapper messageMapper;
    private final Pusher pusher;

    public MessageService(ChatService chatService, MessageMapper messageMapper, Pusher pusher) {
        this.chatService = chatService;
        this.messageMapper = messageMapper;
        this.pusher = pusher;
    }

    public MessageEntity sendMessage(MessageEntity messageEntity) {
        if(messageEntity.getSender_id() == null){
            throw new RuntimeException("User not found");
        }

        Long chatRoomId = chatService.getChatRoomIdByMember(messageEntity.getSender_id(), messageEntity.getReceiver_id());
        messageEntity.setChat_id(chatRoomId);

        int affectedRows = messageMapper.addMessage(messageEntity);
        if (affectedRows == 0) {
            throw new RuntimeException("Failed to send message");
        }

        String channelName = "chat." + messageEntity.getReceiver_id();
        String eventName = "incoming-msg";
        pusher.trigger(channelName, eventName, messageEntity);

        return messageEntity;
    }

    public List<MessageEntity> getMessages(ChatEntity chatEntity, Long meId) {
        List<MessageEntity> messagesByUserId = messageMapper.getMessagesByChat(chatEntity);
        if(messagesByUserId.size() > 0) {
            Long receiverId = chatEntity.getUser1_id().equals(meId) ? chatEntity.getUser2_id() : chatEntity.getUser1_id();
            updateIsReadByChat(chatEntity.getId(), meId, receiverId);
        }
        return messagesByUserId;
    }

    public void updateIsReadByChat(Long chatId, Long senderId, Long receiverId) {
        int affectedRows = messageMapper.updateIsReadByChatId(chatId, senderId);
        if(affectedRows > 0) {
            String channelName = "chat." + receiverId;
            String eventName = "read";
            pusher.trigger(channelName, eventName, chatId);
        }
    }

}
