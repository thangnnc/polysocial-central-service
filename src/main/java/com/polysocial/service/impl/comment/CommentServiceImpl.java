package com.polysocial.service.impl.comment;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.polysocial.consts.CommentAPI;
import com.polysocial.consts.PostAPI;
import com.polysocial.dto.CommentDTO;
import com.polysocial.dto.NotificationsDTO;
import com.polysocial.service.comment.CommentService;
import com.polysocial.service.notifications.NotificationsService;
import com.polysocial.notification.ContentNotifications;
import com.polysocial.repo.PostRepo;
import com.polysocial.repo.UserRepo;
import com.polysocial.type.TypeNotifications;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private NotificationsService notificationsService;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PostRepo postRepo;

	@Override
	public CommentDTO createComment(CommentDTO dto, Long tokenId) {
		try {
			String url = CommentAPI.API_CREATE_COMMENT;
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			dto.setUserId(tokenId);
			HttpEntity entity = new HttpEntity(dto, header);
			ResponseEntity<CommentDTO> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity,
					CommentDTO.class);
			NotificationsDTO noti = new NotificationsDTO(
					String.format(ContentNotifications.NOTI_CONTENT_COMMENT_POST,  userRepo.findById(tokenId).get().getFullName()),
					TypeNotifications.NOTI_TYPE_COMMENT_POST, postRepo.findByPostId(dto.getPostId()).getCreatedBy());
			notificationsService.createNoti(noti);
			return responseEntity.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CommentDTO deleteById(Long commentId) throws Exception {
		try {
			String url = CommentAPI.API_DELETE_COMMENT;
			UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
					.queryParam("commentId", commentId)
					.build();
			restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, null,
					CommentDTO.class);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CommentDTO findAllPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentDTO save(CommentDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentDTO update(CommentDTO dto, Long tokenId) throws Exception {
		try {
			String url = CommentAPI.API_PUT_COMMENT;
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			dto.setUserId(tokenId);
			HttpEntity entity = new HttpEntity(dto, header);
			ResponseEntity<CommentDTO> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity,
					CommentDTO.class);
			return responseEntity.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CommentDTO> getCommentByPostId(Long postId, Optional<Integer> page, Optional<Integer> size) {
		try {
			String url = CommentAPI.API_GET_COMMENT_BY_POST;
			UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
					.queryParam("postId", postId)
					.queryParam("page", page)
					.queryParam("size", size)
					.build();
			ResponseEntity<Object> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
					Object.class);
			return (List<CommentDTO>) entity.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CommentDTO replyComment(CommentDTO dto) {
		try {
			String url = CommentAPI.API_REPLY_COMMENT;
			HttpHeaders hedear = new HttpHeaders();
			hedear.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<CommentDTO> httpEntity = new HttpEntity(dto, hedear);
			ResponseEntity<CommentDTO> entity = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
					CommentDTO.class);
			return entity.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
