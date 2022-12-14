package com.polysocial.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterRequestDTO {

    private String fullName;

    private String studentCode;

    private String email;

    private String password;

    private String avatar;

    private String birthdayStr;

    private LocalDate birthday;

    private boolean gender;

    private String address;

    private String major;

    private String course;

    private String role;

    private MultipartFile avatarFile;

}
