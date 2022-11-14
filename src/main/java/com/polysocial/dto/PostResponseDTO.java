package com.polysocial.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.polysocial.entity.Users;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDTO {
    
    private Long postId;

    private String content;

    private LocalDateTime createdDate;
    
    private Users user;

    private Long groupId;
    
    private Long countLike;
    
    private Long countComment;
    
    private List<CommentResponseDTO> listComment;
    
    private List<PostFileResponseDTO> listUrl;
}
