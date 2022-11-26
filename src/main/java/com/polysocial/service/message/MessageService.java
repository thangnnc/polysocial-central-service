package com.polysocial.service.message;

import java.util.List;

import com.polysocial.dto.MessageDTO;

public interface MessageService {

	MessageDTO createMessage(MessageDTO dto);

	List<Object> getMessageContent(Long roomId);


}
