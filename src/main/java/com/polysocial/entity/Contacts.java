package com.polysocial.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Contacts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;

    private Boolean isAdmin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private Users user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roomId", insertable = false, updatable = false)
    private RoomChats room;

    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Messages> message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Contacts contacts = (Contacts) o;
        return contactId != null && Objects.equals(contactId, contacts.contactId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
