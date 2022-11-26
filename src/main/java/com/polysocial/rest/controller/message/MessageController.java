package com.polysocial.rest.controller.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.consts.CentralAPI;
import com.polysocial.dto.MessageDTO;
import com.polysocial.dto.ResponseDTO;
import com.polysocial.dto.RoomChatRequestDTO;
import com.polysocial.service.message.MessageService;
import com.polysocial.utils.ValidateUtils;

@CrossOrigin("*")
@RestController
public class MessageController {

	@Autowired
	private MessageService messageService;

	@PostMapping(CentralAPI.CREATE_MESSAGE)
	public ResponseEntity createMessage(@RequestBody MessageDTO request) {
		if (ValidateUtils.isNullOrEmpty(request.getContent())) {
			ResponseDTO response = new ResponseDTO();
			response.setStatus(HttpStatus.BAD_REQUEST);
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		} else {
			MessageDTO response = messageService.createMessage(request);
			return ResponseEntity.ok(response);
		}
	}
	
//	@PostMapping(CentralAPI.GET_MESSAGE)
//	public ResponseEntity getMessage(@RequestBody RoomChatRequestDTO request) {
//		System.out.println("---"+request.getRoomId());
//		if (ValidateUtils.isNullOrEmpty(request.getRoomId())) {
//			ResponseDTO response = new ResponseDTO();
//			response.setStatus(HttpStatus.BAD_REQUEST);
//			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
//		} else {
//			List<MessageContentDTO> response = messageService.getMessageContent(request.getRoomId());
//			return ResponseEntity.ok(response);
//		}
//	}
	
	@PostMapping(CentralAPI.GET_MESSAGE)
	public ResponseEntity getMessage(@RequestBody RoomChatRequestDTO request) {
		System.out.println("---"+request.getRoomId());
		if (ValidateUtils.isNullOrEmpty(request.getRoomId())) {
			ResponseDTO response = new ResponseDTO();
			response.setStatus(HttpStatus.BAD_REQUEST);
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		} else {
			List<Object> response = messageService.getMessageContent(request.getRoomId());
			return ResponseEntity.ok(response);
		}
	}
}
