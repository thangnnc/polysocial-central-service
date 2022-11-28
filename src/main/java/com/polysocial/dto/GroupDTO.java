package com.polysocial.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class GroupDTO implements Serializable{
    private Long groupId;

    private String name;

    private Long totalMember;

    private String description;

    private Boolean status;

    private LocalDateTime createdDate = LocalDateTime.now();

    private String className;

    private Long adminId;

    private String avatar;

}
