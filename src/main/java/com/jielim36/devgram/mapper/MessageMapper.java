package com.jielim36.devgram.mapper;

import com.jielim36.devgram.entity.ChatEntity;
import com.jielim36.devgram.entity.MessageEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {

    int addMessage(MessageEntity messageEntity);

    List<MessageEntity> getMessagesByChat(ChatEntity chatEntity);

    int updateIsReadByChatId(Long chat_id, Long sender_id);

    int deleteMessageById(Long message_id);

}
