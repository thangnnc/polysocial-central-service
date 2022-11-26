package com.polysocial.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskFileDTO {
    private Long taskFileId;

    private String url;

    private String type;

    private LocalDateTime createdDate = LocalDateTime.now();

    private Long taskId;
}