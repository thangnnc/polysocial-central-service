package com.polysocial.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polysocial.entity.id.FriendId;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@IdClass(FriendId.class)
public class Friends implements Serializable {

    @Id
    @Column(name = "userInvite")
    private Long userInviteId;

    @Id
    @Column(name = "userConfirm")
    private Long userConfirmId;

    private Boolean status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userInvite", insertable = false, updatable = false)
    private Users userInvite;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userConfirm", insertable = false, updatable = false)
    private Users userConfirm;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Friends friends = (Friends) o;
        return userInviteId != null && Objects.equals(userInviteId, friends.userInviteId)
                && userConfirmId != null && Objects.equals(userConfirmId, friends.userConfirmId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userInviteId, userConfirmId);
    }
}
