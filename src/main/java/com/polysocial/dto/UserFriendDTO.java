package com.polysocial.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserFriendDTO implements Serializable{
    private String token;

    private String fullName;

    private String studentCode;

    private String email;

    private Long status;

    private Boolean isConfirm;

    private String avatar;

    private String role;

    private Long userId;

    private List<ContactDTO> listContact;

    private Long roomId;

}
