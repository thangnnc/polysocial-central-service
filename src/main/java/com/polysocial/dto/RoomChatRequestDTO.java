package com.polysocial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomChatRequestDTO {

	private Long roomId;

	private Long page;

	private Long limit;

}
