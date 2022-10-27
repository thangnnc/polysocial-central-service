package com.polysocial.entity;

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
public class Exercises implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exId;

    private String content;

    private LocalDateTime createdDate;

    private LocalDateTime endDate;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "groupId", insertable = false, updatable = false)
    private Groups group;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Exercises exercises = (Exercises) o;
        return exId != null && Objects.equals(exId, exercises.exId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
