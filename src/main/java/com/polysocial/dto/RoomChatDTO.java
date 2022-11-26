package com.polysocial.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RoomChatDTO {
	
	private Long groupId;

	private Boolean status;

	private LocalDate createDate;
}
