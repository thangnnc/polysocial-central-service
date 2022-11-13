package com.polysocial.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Groups implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    private String name;

    private Long totalMember;

    private String description;

    private Boolean status = true;

    private Date createdDate = new Date();

    @JsonManagedReference
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Posts> posts;

    @JsonManagedReference
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Exercises> exercises;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Groups groups = (Groups) o;
        return groupId != null && Objects.equals(groupId, groups.groupId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
