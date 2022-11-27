package com.polysocial.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FriendDetailDTO implements Serializable {
    private Long userConfirmId;
    private Long userInviteId;
    private String fullNameUserConfirm;
    private String fullNameUserInvite;
    private Boolean status = false;
    private String avatarUserInvite;


    public FriendDetailDTO(Long userConfirmId, Long userInviteId, String fullNameUserConfirm,
            String fullNameUserInvite, String avatarUserInvite) {
        this.userConfirmId = userConfirmId;
        this.userInviteId = userInviteId;
        this.fullNameUserConfirm = fullNameUserConfirm;
        this.fullNameUserInvite = fullNameUserInvite;
        this.avatarUserInvite = avatarUserInvite;
    }
}
