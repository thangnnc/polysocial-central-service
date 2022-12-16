package com.polysocial.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class ViewedStatus implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long viewedStatusId;
	
	private Long contactId;
	
	private LocalDateTime lastUpdateDate = LocalDateTime.now();
	
	private Boolean status = false;
	

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contactId", updatable = false, insertable = false)
    private Contacts contact;

}
