package com.polysocial.entity.id;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class FriendId implements Serializable {

    @Column
    private Long userInviteId;

    @Column
    private Long userConfirmId;

}
