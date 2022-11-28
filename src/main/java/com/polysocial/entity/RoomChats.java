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

    private Boolean status = true;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupId")
    private Groups group;
    


    public RoomChats(Groups group) {
        this.group = group;
    }


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
