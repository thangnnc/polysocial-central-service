package com.polysocial.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdatePasswordDTO implements Serializable{
    private Long userId;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
