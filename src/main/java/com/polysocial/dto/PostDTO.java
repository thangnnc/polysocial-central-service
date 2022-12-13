package com.polysocial.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDTO implements Serializable {

	private Long postId;

	private String content;

	private Long createdBy;

	private Long groupId;

	private List<MultipartFile> files;

	private String type;


}
