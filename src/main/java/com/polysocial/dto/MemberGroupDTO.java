package com.polysocial.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberGroupDTO implements Serializable {
    private Long groupId;
    private Long userId;
    private String groupName;
    private Long totalMember;
    private String className;
    private String avatarGroup;
    private Long roomId;
    private List<ContactDTO> listContact;
    private String description;
    private LocalDateTime createdDate;

    public MemberGroupDTO(Long groupId, Long userId, String groupName, Long totalMember, String avatarGroup,
            Long roomId) {
        this.groupId = groupId;
        this.userId = userId;
        this.groupName = groupName;
        this.totalMember = totalMember;
        this.avatarGroup = avatarGroup;
        this.roomId = roomId;
    }
}
