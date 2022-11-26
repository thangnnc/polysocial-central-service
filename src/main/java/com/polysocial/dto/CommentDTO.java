package com.polysocial.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.polysocial.entity.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO implements Serializable {

    private Long cmtId;

    private String content;

    private LocalDateTime createdDate = LocalDateTime.now();

    private Long postId;

    private Long userId;
    
    private Users user;

    private List<CommentReplyDTO> commentReplies;

    private Long idReply;

    public CommentDTO(String content, Long postId, Long userId, Users user, List<CommentReplyDTO> commentReplies) {
        this.content = content;
        this.postId = postId;
        this.userId = userId;
        this.user = user;
        this.commentReplies = commentReplies;
    }
}