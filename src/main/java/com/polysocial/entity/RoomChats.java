package com.polysocial.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class RoomChats implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    private String name;

    private Boolean status;

    private Integer type;

    private LocalDateTime createdDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Contacts> contacts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RoomChats roomChats = (RoomChats) o;
        return roomId != null && Objects.equals(roomId, roomChats.roomId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
