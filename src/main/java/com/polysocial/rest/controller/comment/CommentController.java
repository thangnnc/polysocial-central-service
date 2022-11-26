package com.polysocial.rest.controller.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.config.jwt.JwtTokenProvider;
import com.polysocial.consts.CentralAPI;
import com.polysocial.dto.CommentDTO;
import com.polysocial.dto.PostDTO;
import com.polysocial.dto.ResponseDTO;
import com.polysocial.service.comment.CommentService;
import com.polysocial.utils.ValidateUtils;

@CrossOrigin("*")
@RestController
public class CommentController {

	@Autowired
	private JwtTokenProvider jwt;

	@Autowired
	private CommentService commentService;

	@PostMapping(CentralAPI.CREATE_COMMENT)
	public ResponseEntity createComment(@RequestBody CommentDTO request, @RequestHeader("Authorization") String token) {
		if (ValidateUtils.isNullOrEmpty(request.getContent())) {
			ResponseDTO response = new ResponseDTO();
			response.setStatus(HttpStatus.BAD_REQUEST);
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		} else {
			Long tokenId = jwt.getIdFromJWT(token);
			CommentDTO response = commentService.createComment(request, tokenId);
			return ResponseEntity.ok(response);
		}
	}

	@PutMapping(CentralAPI.UPDATE_COMMENT)
	public ResponseEntity updateComment(@RequestBody CommentDTO request, @RequestHeader("Authorization") String token) throws Exception {
		if (ValidateUtils.isNullOrEmpty(request.getContent())) {
			ResponseDTO response = new ResponseDTO();
			response.setStatus(HttpStatus.BAD_REQUEST);
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		} else {
			Long tokenId = jwt.getIdFromJWT(token);
			CommentDTO response = commentService.update(request, tokenId);
			return ResponseEntity.ok(response);
		}
	}

	@DeleteMapping(CentralAPI.DELETE_COMMENT)
	public ResponseEntity deleteComment(@RequestParam Long commentId) throws Exception {
		try{
			CommentDTO response = commentService.deleteById(commentId);
			return ResponseEntity.ok(response);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(CentralAPI.GET_COMMENT_BY_POST_ID)
	public ResponseEntity getCommentByPost(@RequestParam Long postId) {
		try{
			List<CommentDTO> response = commentService.getCommentByPostId(postId);
			return ResponseEntity.ok(response);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
		}
	}
}
