package com.polysocial.rest.controller.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.config.jwt.JwtTokenProvider;
import com.polysocial.dto.LikeDTO;
import com.polysocial.dto.ResponseDTO;
import com.polysocial.service.like.LikeService;
import com.polysocial.utils.ValidateUtils;
import com.polysocial.consts.CentralAPI;

@CrossOrigin("*")
@RestController
public class LikeController {

	@Autowired
	private LikeService likeService;
	
	@Autowired
	private JwtTokenProvider jwt;
	
	@PostMapping(CentralAPI.LIKE)
	public ResponseEntity likeUnLike(@RequestBody LikeDTO request,@RequestHeader("Authorization") String token) {
		if (ValidateUtils.isNullOrEmpty(request.getPostId())) {
			ResponseDTO response = new ResponseDTO();
			response.setStatus(HttpStatus.BAD_REQUEST);
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		} else {
			System.out.println("dto"+request);
			Long tokenId = jwt.getIdFromJWT(token);
			LikeDTO response = likeService.likeUnLike(request, tokenId);
			return ResponseEntity.ok(response);
		}
	}
}
