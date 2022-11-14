package com.polysocial.service.comment;

import com.polysocial.dto.CommentDTO;

public interface CommentService {

	CommentDTO createComment(CommentDTO dto, Long tokenId);

}
