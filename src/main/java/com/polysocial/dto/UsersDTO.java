package com.polysocial.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersDTO {

    private String token;

    private String fullName;

    private String studentCode;

    private String email;

    private String avatar;

    private String role;

    private Long userId;


}