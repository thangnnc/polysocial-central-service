package com.polysocial.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberGroupDTO implements Serializable{
    private Long groupId;
    private Long userId;
    private String groupName;
    private Long totalMember;

    public MemberGroupDTO(Long groupId, Long userId,  String groupName, Long totalMember ) {
        this.groupId = groupId;
        this.userId = userId;
        this.groupName = groupName;
        this.totalMember = totalMember;
    }
}
