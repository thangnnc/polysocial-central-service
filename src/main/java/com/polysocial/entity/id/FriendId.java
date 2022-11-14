package com.polysocial.entity.id;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class FriendId implements Serializable {

    @Column(name="userInvite")
    private Long userInviteId;

    @Column(name="userConfirm")
    @Column(name = "userInvite")
    private Long userInviteId;
    @Column(name = "userConfirm")
    private Long userConfirmId;

}
