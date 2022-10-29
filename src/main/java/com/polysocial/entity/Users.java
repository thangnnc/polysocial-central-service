package com.polysocial.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String fullName;

    private String studentCode;

    private String email;

    private String password;

    private String avatar;

    private boolean isActive;

    private LocalDateTime createdDate;

    private Long roleId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "roleId", insertable = false, updatable = false)
    private Roles role;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private UserDetail userDetail;

    @JsonManagedReference
    @OneToMany(mappedBy = "userInvite", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Friends> friendInvites;

    @JsonManagedReference
    @OneToMany(mappedBy = "userConfirm", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Friends> friendConfirms;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Notifications> notification;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Contacts> contacts;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Posts> posts;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Likes> likes;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Comments> comments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Users users = (Users) o;
        return userId != null && Objects.equals(userId, users.userId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
