package com.polysocial.consts;

import org.springframework.stereotype.Component;

@Component
public class MessageAPI {

public static final String API_CREATE_MESSAGE = HostURL.MESSAGE_HOST + "/api/createMessage";

	
	public static final String API_GET_MESSAGE = HostURL.MESSAGE_HOST + "/api/getMessage";
}
