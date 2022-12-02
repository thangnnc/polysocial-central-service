package com.polysocial.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RoomChatDTO {
	
	private Long roomId;

	private Boolean status;

	private Long groupId;

	private LocalDateTime lastUpdateDate;
	
	private String lastMessage;;
}
