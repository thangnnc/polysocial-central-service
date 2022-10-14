package com.polysocial.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String fullName;

    private String studentCode;

    private String email;

    private String password;

    private String avatar;

    private boolean isActive;

    private Integer roleId;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "roleId", insertable = false, updatable = false)
    private Role role;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private UserDetail userDetail;

}
