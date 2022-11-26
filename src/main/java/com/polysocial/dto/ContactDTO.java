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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.polysocial.entity.RoomChats;
import com.polysocial.entity.Users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactDTO implements Serializable{
    private Long userId;
    private String fullName;
    private String email;
    private String avatar;

    public ContactDTO(Long userId, String fullName, String email, String avatar) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.avatar = avatar;
    }
}
// @Data
// public class ContactDTO{
	
// 	private Long contactId;
	
// 	private Boolean isAdmin;
	
// 	private Long userId;
	
// 	private Long roomId;
