package com.polysocial.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskExDTO {
    private Long taskId;

    private Long exId;

    private Long userId;

    private Long groupId;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    private Float mark;

    public TaskExDTO(Long exId, Long userId, Long groupId) {
        this.exId = exId;
        this.userId = userId;
        this.groupId = groupId;
    }
}
