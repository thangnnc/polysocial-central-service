package com.polysocial.consts;

import org.springframework.stereotype.Component;

@Component
public class RoomChatAPI {
	
	public static final String API_CREATE_ROOM_CHAT = HostURL.MESSAGE_HOST + "/api/createChatRoom";
	
	public static final String API_GET_ROOM_CHAT = HostURL.MESSAGE_HOST + "/api/getName";
}
