package com.polysocial.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class ViewedStatus {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ViewedStatusId;
	
	private Long contactId;
	
	private LocalDateTime lastUpdateDate;
	
	private Boolean status;
}
