package com.jielim36.devgram.mapper;

import com.jielim36.devgram.DTO.ChatDTO;
import com.jielim36.devgram.entity.ChatEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMapper {

    int createChatRoom(ChatEntity chatEntity);

    Long getChatRoomIdByMember(ChatEntity chatEntity);

    ChatDTO[] getChatRoomsByUserId(Long user_id);

}
