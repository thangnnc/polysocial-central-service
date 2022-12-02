package com.polysocial.service.impl.roomChat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.polysocial.consts.MessageAPI;
import com.polysocial.consts.PostAPI;
import com.polysocial.consts.RoomChatAPI;
import com.polysocial.dto.GroupNameDTO;
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
	
	@Override
	public List<Object> getNameGroupDESC(GroupNameDTO dto){
		List<Object> list = new ArrayList<>();
		try {
			String url = RoomChatAPI.API_GET_ROOM_CHAT;
			HttpHeaders hedear = new HttpHeaders();
			hedear.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object[]> httpEntity = new HttpEntity(dto, hedear);
			try {
				ResponseEntity<Object[]> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
						Object[].class);
				for (int i = 0; i < response.getBody().length; i++) {
					list.add(response.getBody()[i]);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
}
