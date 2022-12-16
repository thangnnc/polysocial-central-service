package com.polysocial.dto;

import java.io.Serializable;
import java.util.List;

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
    private String friendName;
    private String friendAvatar;
    private String friendEmail;
    private String avatarUserInvite;
    private String avatarUserConfirm;
    private List<ContactDTO> listContact;
    private long groupId;
    private Long roomId;
    private String emailInvite;


    public FriendDetailDTO(Long userConfirmId, Long userInviteId, String fullNameUserConfirm,
            String fullNameUserInvite, String avatarUserInvite, String avatarUserConfirm) {
        this.userConfirmId = userConfirmId;
        this.userInviteId = userInviteId;
        this.fullNameUserConfirm = fullNameUserConfirm;
        this.fullNameUserInvite = fullNameUserInvite;
        this.avatarUserInvite = avatarUserInvite;
        this.avatarUserConfirm = avatarUserConfirm;
    }
}
