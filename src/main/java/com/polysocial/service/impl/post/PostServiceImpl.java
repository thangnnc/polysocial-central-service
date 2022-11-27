package com.polysocial.service.impl.post;

import com.polysocial.consts.PostAPI;
import com.polysocial.dto.ListPostDTO;
import com.polysocial.dto.NotificationsDTO;
import com.polysocial.dto.PostDTO;
import com.polysocial.dto.PostDTO2;
import com.polysocial.entity.Members;
import com.polysocial.notification.ContentNotifications;
import com.polysocial.repo.GroupRepo;
import com.polysocial.repo.MemberRepo;
import com.polysocial.repo.UserRepo;
import com.polysocial.service.notifications.NotificationsService;
import com.polysocial.service.post.PostService;
import com.polysocial.type.TypeNotifications;
import com.polysocial.utils.UploadToCloud;

import java.io.IOException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
    private UploadToCloud uploadToCloud;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private NotificationsService notificationsService;	

	@Autowired
	private GroupRepo groupRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private MemberRepo	memberRepo;

	@Override
	public ListPostDTO getAllPosts(Integer page, Integer limit) {
		try {
			String url = PostAPI.API_GET_ALL_POSTS;
			UriComponents builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("page", page)
					.queryParam("limit", limit).build();
			HttpHeaders hedear = new HttpHeaders();
			hedear.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity entity = new HttpEntity<>(hedear);
			ResponseEntity<ListPostDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
					ListPostDTO.class);
			return response.getBody();
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
			List<String> listPath = saveFileUrl(dto.getFiles());
			PostDTO2 dto2 = modelMapper.map(dto, PostDTO2.class);
			dto2.setListPath(listPath);
			HttpEntity<PostDTO> httpEntity = new HttpEntity(dto2, hedear);
			ResponseEntity<PostDTO> entity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, PostDTO.class);
			String groupName = groupRepo.findById(dto.getGroupId()).get().getName();
			String adminName = userRepo.findById(tokenId).get().getFullName();
			List<Members> listMember = memberRepo.findByGroupId(dto.getGroupId());
			for (Members members : listMember) {
				NotificationsDTO noti = new NotificationsDTO(String.format(ContentNotifications.NOTI_CONTENT_ADMIN_POST,adminName , groupName), TypeNotifications.NOTI_TYPE_ADMIN_POST, members.getUserId());
				notificationsService.createNoti(noti);
			}
			return entity.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> saveFileUrl(List<MultipartFile> files) throws IOException {
		return uploadToCloud.saveFilePost(files);
	}

	@Override
	public PostDTO updatePost(PostDTO dto, Long tokenId) {
		try {
			String url = PostAPI.API_UPDATE_POST;
			HttpHeaders hedear = new HttpHeaders();
			hedear.setContentType(MediaType.APPLICATION_JSON);
			dto.setCreatedBy(tokenId);
			//API GROUP			
			List<String> listPath = saveFileUrl(dto.getFiles());
			PostDTO2 dto2 = modelMapper.map(dto, PostDTO2.class);
			dto2.setListPath(listPath);
			HttpEntity<PostDTO> httpEntity = new HttpEntity(dto2, hedear);
			ResponseEntity<PostDTO> entity = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, PostDTO.class);
			String groupName = groupRepo.findById(dto.getGroupId()).get().getName();
			String adminName = userRepo.findById(tokenId).get().getFullName();
			List<Members> listMember = memberRepo.findByGroupId(dto.getGroupId());
			for (Members members : listMember) {
				NotificationsDTO noti = new NotificationsDTO(String.format(ContentNotifications.NOTI_CONTENT_ADMIN_POST,adminName , groupName), TypeNotifications.NOTI_TYPE_ADMIN_POST, members.getUserId());
				notificationsService.createNoti(noti);
			}
			return entity.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PostDTO getOne(Long postId) {
		try {
			String url = PostAPI.API_GET_ONE_POST;
			UriComponents builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("postId", postId).build();
			HttpHeaders hedear = new HttpHeaders();
			hedear.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity entity = new HttpEntity<>(hedear);
			ResponseEntity<PostDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
					PostDTO.class);
			return response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void delete(Long postId) {
		try {
			String url = PostAPI.API_DELETE_POST;
			UriComponents builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("postId", postId).build();
			HttpHeaders hedear = new HttpHeaders();
			hedear.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity entity = new HttpEntity<>(hedear);
			restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, entity,
					String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
