package com.polysocial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.polysocial.entity.id.FriendId;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@IdClass(FriendId.class)
public class Friends implements Serializable {

    @Id
    private Long userInviteId;

    @Id
    private Long userConfirmId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupId")
    private Groups group;

    private Boolean status = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private Boolean isFriend  = true;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userInvite", insertable = false, updatable = false)
    private Users userInvite;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userConfirm", insertable = false, updatable = false)
    private Users userConfirm;

}
