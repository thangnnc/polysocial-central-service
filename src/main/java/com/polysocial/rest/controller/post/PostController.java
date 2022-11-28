package com.polysocial.rest.controller.post;

import com.polysocial.config.jwt.JwtTokenProvider;
import com.polysocial.consts.CentralAPI;
import com.polysocial.dto.ListPostDTO;
import com.polysocial.dto.PostDTO;
import com.polysocial.dto.PostDTO2;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@DeleteMapping(CentralAPI.API_DELETE_POST)
	public ResponseEntity deletePost(@RequestParam("postId") Long postId) {
		try{
			postService.delete(postId);
			return ResponseEntity.ok().build();
		}catch(Exception e){
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(CentralAPI.API_GET_ONE_POST)
	public ResponseEntity getOnePost(@RequestParam("postId") Long postId) {
		PostDTO response = postService.getOne(postId);
		return ResponseEntity.ok(response);
	}

	@GetMapping(CentralAPI.API_GET_POST_BY_GROUP)
	public ResponseEntity getAllPostByGroup(@RequestParam("groupId") Long groupId,
			@RequestParam("page") Optional<Integer> page, @RequestParam("limit") Optional<Integer> limit) {
		ListPostDTO response = postService.findAllPageByGroup(groupId, page.orElse(0), limit.orElse(10));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
