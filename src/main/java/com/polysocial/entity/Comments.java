package com.polysocial.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Comments implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cmtId;

    private String content;

    private Boolean status;

    private LocalDateTime createdDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="manageCmt", insertable = false, updatable = false)
    private Comments manager;

    @JsonManagedReference
    @OneToMany(mappedBy="manager")
    private List<Comments> comments;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "postId", insertable = false, updatable = false)
    private Posts post;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comments comments = (Comments) o;
        return cmtId != null && Objects.equals(cmtId, comments.cmtId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
