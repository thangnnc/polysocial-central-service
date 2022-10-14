package com.polysocial.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class UserDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userDetailCode;

    private LocalDate birthday;

    private boolean gender;

    private String address;

    private String major;

    private String course;

    private LocalDate createdDate;

    @OneToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private Users user;
}
