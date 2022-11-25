package com.polysocial.rest.controller.post;

import com.polysocial.config.jwt.JwtTokenProvider;
import com.polysocial.consts.CentralAPI;
import com.polysocial.dto.ListPostDTO;
import com.polysocial.dto.PostDTO;
import com.polysocial.dto.ResponseDTO;
import com.polysocial.service.post.PostFileService;
import com.polysocial.service.post.PostService;
import com.polysocial.utils.ValidateUtils;
import com.twilio.twiml.fax.Receive.MediaType;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private PostFileService postFileService;

	@Autowired
	private JwtTokenProvider jwt;

	@GetMapping(CentralAPI.GET_ALL_POST)
	public ResponseEntity getAllPost(@RequestParam("page") Optional<Integer> page,
			@RequestParam("limit") Optional<Integer> limit) {
		ListPostDTO response = postService.getAllPosts(page.orElse(0), limit.orElse(10));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = CentralAPI.GET_ALL_POST, consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity createPost(@ModelAttribute PostDTO request, @RequestHeader("Authorization") String token) {
		if (ValidateUtils.isNullOrEmpty(request.getContent())) {
			ResponseDTO response = new ResponseDTO();
			response.setStatus(HttpStatus.BAD_REQUEST);
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		} else {
			Long tokenId = jwt.getIdFromJWT(token);
			PostDTO response = postService.createPost(request, tokenId);
			return ResponseEntity.ok(response);
		}
	}

	@PutMapping(value = CentralAPI.API_UPDATE_POST, consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity updatePost(@ModelAttribute PostDTO request, @RequestHeader("Authorization") String token) {
		if (ValidateUtils.isNullOrEmpty(request.getContent())) {
			ResponseDTO response = new ResponseDTO();
			response.setStatus(HttpStatus.BAD_REQUEST);
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		} else {
			Long tokenId = jwt.getIdFromJWT(token);
			PostDTO response = postService.updatePost(request, tokenId);
			return ResponseEntity.ok(response);
		}
	}
}
