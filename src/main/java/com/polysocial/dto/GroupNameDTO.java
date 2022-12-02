package com.polysocial.dto;

import java.util.List;

import lombok.Data;

@Data
public class GroupNameDTO {

	private Long roomId;

	private String name;
	
	private String avatar;

	private String lastMessage;

	private Long userId;
	
	private Long totalMember;
	
	private List<Object[]> listContacts;
}
