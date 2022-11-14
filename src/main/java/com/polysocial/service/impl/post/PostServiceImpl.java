package com.polysocial.service.impl.post;

import com.polysocial.consts.PostAPI;
import com.polysocial.dto.ListPostDTO;
import com.polysocial.dto.PostDTO;
import com.polysocial.service.post.PostService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ListPostDTO getAllPosts(Integer page, Integer limit) {
		try {
			String url = PostAPI.API_GET_ALL_POSTS;
			UriComponents builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("page", page)
					.queryParam("limit", limit).build();

			ResponseEntity<ListPostDTO> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
					ListPostDTO.class);
			return entity.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PostDTO createPost(PostDTO dto, Long tokenId) {
		try {

			String url = PostAPI.API_CREATE_POST;
			HttpHeaders hedear = new HttpHeaders();
			hedear.setContentType(MediaType.APPLICATION_JSON);
			dto.setCreatedBy(tokenId);
			//API GROUP
			dto.setGroupId(2l);
			
			HttpEntity<PostDTO> httpEntity = new HttpEntity(dto, hedear);
			ResponseEntity<PostDTO> entity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, PostDTO.class);
			return entity.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
