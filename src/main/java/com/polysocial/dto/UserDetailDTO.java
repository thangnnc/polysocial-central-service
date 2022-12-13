package com.polysocial.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.polysocial.entity.Users;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailDTO implements Serializable {

    private Long userDetailId;

    private LocalDateTime birthday;

    private boolean gender;

    private String address;

    private String major;

    private String course;

    private LocalDateTime createdDate;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Long userId;
}
