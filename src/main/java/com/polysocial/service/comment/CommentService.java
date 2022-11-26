package com.polysocial.service.comment;

import java.util.List;

import com.polysocial.dto.CommentDTO;

public interface CommentService {

	CommentDTO createComment(CommentDTO dto, Long tokenId);

	CommentDTO deleteById(Long id) throws Exception;

    CommentDTO findAllPage();

    CommentDTO save(CommentDTO dto);

    CommentDTO update(CommentDTO dto, Long tokenId) throws Exception;
    
    List<CommentDTO> getCommentByPostId(Long postId);

    CommentDTO replyComment(CommentDTO dto);
}
