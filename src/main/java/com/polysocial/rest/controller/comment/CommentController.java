package com.polysocial.rest.controller.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
}
