package com.polysocial.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberGroupDTO implements Serializable{
    private Long groupId;
    private Long userId;
    private String groupName;
    private Long totalMember;
    private List<ContactDTO> listContact;


    public MemberGroupDTO(Long groupId, Long userId,  String groupName, Long totalMember ) {
        this.groupId = groupId;
        this.userId = userId;
        this.groupName = groupName;
        this.totalMember = totalMember;
    }
}
