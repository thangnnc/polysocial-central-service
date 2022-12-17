package com.polysocial.entity;

import lombok.*;
import org.hibernate.Hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class UserDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userDetailId;

    private LocalDateTime birthday;

    private boolean gender;

    private String address;

    private String major;

    private String course;

    private Long userId;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "userId" ,insertable=false ,updatable=false)
    private Users user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserDetail that = (UserDetail) o;
        return userDetailId != null && Objects.equals(userDetailId, that.userDetailId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
