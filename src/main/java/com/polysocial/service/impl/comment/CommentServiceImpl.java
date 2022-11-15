package com.polysocial.service.impl.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.polysocial.consts.CommentAPI;
import com.polysocial.consts.PostAPI;
import com.polysocial.dto.CommentDTO;
import com.polysocial.service.comment.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Override
	public CommentDTO createComment(CommentDTO dto, Long tokenId) {
		try {
			
			String url = CommentAPI.API_CREATE_POST;
			HttpHeaders hedear = new HttpHeaders();
			hedear.setContentType(MediaType.APPLICATION_JSON);
			dto.setUserId(tokenId);
			HttpEntity<CommentDTO> httpEntity = new HttpEntity(dto, hedear);
			ResponseEntity<CommentDTO> entity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, CommentDTO.class);
			return entity.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
