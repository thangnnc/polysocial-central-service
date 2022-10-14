package com.polysocial.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailDTO {

    private Long userDetailCode;

    private LocalDate birthday;

    private boolean gender;

    private String address;

    private String major;

    private String course;

    private LocalDate createdDate;

}
