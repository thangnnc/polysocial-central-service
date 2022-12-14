package com.polysocial.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
