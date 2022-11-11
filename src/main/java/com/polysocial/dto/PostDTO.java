package com.polysocial.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDTO implements Serializable {

	 private Long postId;

	    private String content;

	    private Date createdDate;

	    private Long createdBy;

	    private Long groupId;
	    
	    private Long countLike;
	    
	    private Long countComment;
	    
		private List<CommentDTO> listComment;
}
