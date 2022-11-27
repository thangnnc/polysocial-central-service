package com.polysocial.dto;

import java.io.Serializable;

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
    private String studenCode;

    public ContactDTO(Long userId, String fullName, String email, String avatar, String studentCode) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.avatar = avatar;
        this.studenCode = studentCode;
    }
}
