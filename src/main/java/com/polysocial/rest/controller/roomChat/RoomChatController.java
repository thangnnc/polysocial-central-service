package com.polysocial.rest.controller.roomChat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.config.jwt.JwtTokenProvider;
import com.polysocial.consts.CentralAPI;
import com.polysocial.dto.GroupNameDTO;
import com.polysocial.dto.ResponseDTO;
import com.polysocial.dto.RoomChatDTO;
import com.polysocial.service.roomChat.RoomChatService;
import com.polysocial.utils.ValidateUtils;

@CrossOrigin("*")
@RestController
public class RoomChatController {

	@Autowired
	RoomChatService roomChatService;

	@Autowired
	private JwtTokenProvider jwt;

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

	@PostMapping(CentralAPI.GET_ROOM_NAME)
	public ResponseEntity getNameGroupDESC(@RequestBody GroupNameDTO request,@RequestHeader("Authorization") String token) {
		if (ValidateUtils.isNullOrEmpty(request.getUserId())) {
			ResponseDTO response = new ResponseDTO();
			response.setStatus(HttpStatus.BAD_REQUEST);
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		} else {
			Long tokenId = jwt.getIdFromJWT(token);
			request.setUserId(tokenId);
			List<Object> response = roomChatService.getNameGroupDESC(request);
			return ResponseEntity.ok(response);
		}
	}

}
