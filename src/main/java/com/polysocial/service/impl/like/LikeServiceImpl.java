package com.polysocial.service.impl.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.polysocial.consts.LikeAPI;
import com.polysocial.dto.LikeDTO;
import com.polysocial.dto.NotificationsDTO;
import com.polysocial.service.like.LikeService;
import com.polysocial.service.notifications.NotificationsService;
import com.polysocial.notification.ContentNotifications;
import com.polysocial.repo.UserRepo;
import com.polysocial.type.TypeNotifications;
@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private NotificationsService notificationsService;

	@Autowired
	private UserRepo userRepo;

	@Override
	public LikeDTO likeUnLike(LikeDTO dto, Long tokenId) {
		try {
			String url = LikeAPI.API_GET_POST_LIKE;
			HttpHeaders hedear = new HttpHeaders();
			hedear.setContentType(MediaType.APPLICATION_JSON);
			dto.setUserId(tokenId);
			HttpEntity<LikeDTO> httpEntity = new HttpEntity(dto, hedear);
			ResponseEntity<LikeDTO> entity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, LikeDTO.class);
			NotificationsDTO notificationsDTO = new NotificationsDTO(
					String.format(ContentNotifications.NOTI_CONTENT_LIKE_POST, userRepo.findById(tokenId).get().getFullName()),
					TypeNotifications.NOTI_TYPE_LIKE_POST, dto.getUserId());
			notificationsService.createNoti(notificationsDTO);
			return entity.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
