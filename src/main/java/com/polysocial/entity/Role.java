package com.polysocial.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    private String name;

    private String description;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Users> users;
}
