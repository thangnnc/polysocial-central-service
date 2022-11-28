package com.polysocial.service.comment;

import java.util.List;
import java.util.Optional;

import com.polysocial.dto.CommentDTO;

public interface CommentService {

	CommentDTO createComment(CommentDTO dto, Long tokenId);

	CommentDTO deleteById(Long id) throws Exception;

    CommentDTO findAllPage();

    CommentDTO save(CommentDTO dto);

    CommentDTO update(CommentDTO dto, Long tokenId) throws Exception;
    
    List<CommentDTO> getCommentByPostId(Long postId, Optional<Integer> page, Optional<Integer> size);

    CommentDTO replyComment(CommentDTO dto);
}
