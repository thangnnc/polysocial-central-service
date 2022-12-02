package com.polysocial.service.roomChat;

import java.util.List;

import com.polysocial.dto.GroupNameDTO;
import com.polysocial.dto.RoomChatDTO;

public interface RoomChatService {

	RoomChatDTO createRoomChat(RoomChatDTO dto);

	List<Object> getNameGroupDESC(GroupNameDTO dto);

}
