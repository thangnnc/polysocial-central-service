package com.polysocial.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SavePostDetailDTO implements Serializable{
    private Long savePostId;
    private Long userId;
    private Long postId;
    private LocalDateTime createdDate = LocalDateTime.now();
    private String content;
    private String url;
    private String fullName;
    private LocalDateTime createdDatePost;

}