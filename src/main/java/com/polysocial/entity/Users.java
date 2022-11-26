package com.polysocial.entity;

import lombok.*;
import org.hibernate.Hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    
    private String qrCode;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "roleId", insertable = false, updatable = false)
    private Roles role;
//
    @JsonManagedReference
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @ToString.Exclude
    private UserDetail userDetail;
//
//   @JsonIgnore
//    @OneToMany(mappedBy = "userInvite", fetch = FetchType.LAZY)
//    @ToString.Exclude
//    private List<Friends> friendInvites;

//    @JsonIgnore
//    @OneToMany(mappedBy = "userConfirm", fetch = FetchType.LAZY)
//    @ToString.Exclude
//    private List<Friends> friendConfirms;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    @ToString.Exclude
//    private List<Notifications> notification;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    @ToString.Exclude
//    private List<Contacts> contacts;
//
//    @JsonManagedReference	
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    @ToString.Exclude
//    private List<Posts> posts;
//
//    @JsonManagedReference	
//	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
//	@ToString.Exclude
//	private List<PostFile> posts;
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    @ToString.Exclude
//    private List<Likes> likes;
//    
//    
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    @ToString.Exclude
//    private List<Comments> comments;

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
