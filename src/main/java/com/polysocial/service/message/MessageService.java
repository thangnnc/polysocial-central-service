package com.polysocial.service.message;

import java.util.List;

import com.polysocial.dto.MessageDTO;

public interface MessageService {

	List<Object> getMessageContent(Long roomId);

	MessageDTO createMessage(MessageDTO dto);


}
