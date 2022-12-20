package com.polysocial.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
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

    private MultipartFile avatarFile;

    private List<ContactDTO> listContact;

    private Long roomId;

}
