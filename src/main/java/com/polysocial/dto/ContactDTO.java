package com.polysocial.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
public class ContactDTO{
	
	private Long contactId;
	
	private Boolean isAdmin;
	
	private Long userId;
	
	private Long roomId;
}
