package com.polysocial.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Roles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    private String name;

    private String description;

    @JsonManagedReference
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @ToString.Exclude
    private List<Users> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Roles roles = (Roles) o;
        return roleId != null && Objects.equals(roleId, roles.roleId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
