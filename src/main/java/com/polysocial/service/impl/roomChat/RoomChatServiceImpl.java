package com.polysocial.service.impl.roomChat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.polysocial.consts.PostAPI;
import com.polysocial.consts.RoomChatAPI;
import com.polysocial.dto.RoomChatDTO;
import com.polysocial.service.roomChat.RoomChatService;

@Service
public class RoomChatServiceImpl implements RoomChatService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public RoomChatDTO createRoomChat(RoomChatDTO dto) {
		try {
			String url = RoomChatAPI.API_CREATE_ROOM_CHAT;
			HttpHeaders hedear = new HttpHeaders();
			hedear.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<RoomChatDTO> httpEntity = new HttpEntity(dto,hedear);
			ResponseEntity<RoomChatDTO> entity = restTemplate.exchange(url, HttpMethod.POST, httpEntity,RoomChatDTO.class);
			return entity.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
