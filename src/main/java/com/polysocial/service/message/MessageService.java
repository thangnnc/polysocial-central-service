package com.polysocial.service.message;

import java.util.List;

import com.polysocial.dto.MessageDTO;
import com.polysocial.dto.RoomChatRequestDTO;

public interface MessageService {


	MessageDTO createMessage(MessageDTO dto);

	List<Object> getMessageContent(RoomChatRequestDTO request);

}
