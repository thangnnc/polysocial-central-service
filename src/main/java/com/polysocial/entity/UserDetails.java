package com.polysocial.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class UserDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userDetailCode;

    private LocalDate birthday;

    private boolean gender;

    private String address;

    private String major;

    private String course;

    @OneToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private Users user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserDetails that = (UserDetails) o;
        return userDetailCode != null && Objects.equals(userDetailCode, that.userDetailCode);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
