package com.polysocial.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private String token;

    private String fullName;

    private String studentCode;

    private String email;

    private String password;

    private String avatar;

    @JsonProperty("role")
    private String roleName;

    private boolean isActive;

}
