package com.jielim36.devgram.service;

import com.jielim36.devgram.entity.ChatEntity;
import com.jielim36.devgram.entity.MessageEntity;
import com.jielim36.devgram.entity.UserEntity;
import com.jielim36.devgram.mapper.MessageMapper;
import com.pusher.rest.Pusher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MessageService {

    private final ChatService chatService;
    private final MessageMapper messageMapper;
    private final UserService userService;
    private final FCMService fcmService;
    private final Pusher pusher;

    public MessageService(ChatService chatService,
                          MessageMapper messageMapper,
                          Pusher pusher,
                          UserService userService,
                          FCMService fcmService) {
        this.chatService = chatService;
        this.messageMapper = messageMapper;
        this.pusher = pusher;
        this.userService = userService;
        this.fcmService = fcmService;
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
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);

        // For saving resources, generate a temp created and updated date time for the created message instead of query again this message
        messageEntity.setCreated_at(formattedNow);
        messageEntity.setUpdated_at(formattedNow);

        String channelName = "chat." + messageEntity.getReceiver_id();
        String eventName = "incoming-msg";
        pusher.trigger(channelName, eventName, messageEntity);

        // push notification
        UserEntity sender = userService.selectUserById(messageEntity.getSender_id());
        String sender_avatar_url = sender.getAvatar_url();
        Long receiver_userId = messageEntity.getReceiver_id();
        String title = "You have a new message from " + sender.getUsername();
        String body = messageEntity.getContent();

        fcmService.sendChatNotificationToUser(sender_avatar_url, receiver_userId, title, body);
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
            String eventName = "read-msg";
            pusher.trigger(channelName, eventName, chatId);
        }
    }

    public boolean deleteMessage(MessageEntity messageEntity) {
        Long messageId = messageEntity.getId();
        int affectedRows = messageMapper.deleteMessageById(messageId);
        boolean isSuccess = affectedRows > 0;
        if(isSuccess) {
            // pusher trigger
            String channelName = "chat." + messageEntity.getReceiver_id();
            String eventName = "delete-msg";
            pusher.trigger(channelName, eventName, messageId);
        }

        return isSuccess;
    }

    public boolean updateMessageReaction(MessageEntity messageEntity) {
        int affectedRows = messageMapper.updateMessageReaction(messageEntity);
        boolean isSuccess = affectedRows > 0;
        if(isSuccess) {
            // pusher trigger
            String channelName = "chat." + messageEntity.getSender_id();
            String eventName = "reaction-msg";
            pusher.trigger(channelName, eventName, messageEntity);
        }

        return isSuccess;
    }

    public boolean updateMessageContent(MessageEntity messageEntity) {
        int affectedRows = messageMapper.updateMessageContent(messageEntity);
        boolean isSuccess = affectedRows > 0;
        if(isSuccess) {
            // pusher trigger
            String channelName = "chat." + messageEntity.getReceiver_id();
            String eventName = "content-msg";
            pusher.trigger(channelName, eventName, messageEntity);
        }

        return isSuccess;
    }

}
