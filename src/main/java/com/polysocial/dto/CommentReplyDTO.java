package com.polysocial.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.polysocial.entity.Users;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CommentReplyDTO {
    private String content;

    private LocalDateTime createdDate;

    private Long postId;

    private Long userId;
    
    private Users user;
}
