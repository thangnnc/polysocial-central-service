package com.polysocial.rest.controller.roomChat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.consts.CentralAPI;
import com.polysocial.dto.ResponseDTO;
import com.polysocial.dto.RoomChatDTO;
import com.polysocial.service.roomChat.RoomChatService;
import com.polysocial.utils.ValidateUtils;

@CrossOrigin("*")
@RestController
public class RoomChatController {

	@Autowired
	RoomChatService roomChatService;

	@PostMapping(CentralAPI.CREATE_ROOM_CHAT)
	public ResponseEntity createRoomChat(@RequestBody RoomChatDTO request) {
		if (ValidateUtils.isNullOrEmpty(request.getGroupId())) {
			ResponseDTO response = new ResponseDTO();
			response.setStatus(HttpStatus.BAD_REQUEST);
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		} else {
			RoomChatDTO response = roomChatService.createRoomChat(request);
			return ResponseEntity.ok(response);
		}
	}
}
