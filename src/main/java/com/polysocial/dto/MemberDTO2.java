package com.polysocial.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO2 implements Serializable{
    
    private Long userId;

    private String fullname;

    private String email;

    private String avatar;


    public MemberDTO2(Long userId, String fullname, String email, String avatar) {
        this.userId = userId;
        this.fullname = fullname;
        this.email = email;
        this.avatar = avatar;
    }
    
}
