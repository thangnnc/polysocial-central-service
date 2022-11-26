package com.polysocial.service.impl.message;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.polysocial.consts.MessageAPI;
import com.polysocial.dto.MessageDTO;
import com.polysocial.service.message.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private RestTemplate restTemplate;
	

	@Override
	public MessageDTO createMessage(MessageDTO dto) {
		try {
			String url = MessageAPI.API_CREATE_MESSAGE;
			HttpHeaders hedear = new HttpHeaders();
			hedear.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<MessageDTO> httpEntity = new HttpEntity(dto, hedear);
			ResponseEntity<MessageDTO> entity = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
					MessageDTO.class);
			return entity.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public List<Object> getMessageContent(Long roomId) {
		
//		List<MessageContentDTO> dto = new ArrayList<>();
		List<Object> list = new ArrayList<>();
		try {
			String url = MessageAPI.API_GET_MESSAGE;
			HttpHeaders hedear = new HttpHeaders();
			hedear.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object[]> httpEntity = new HttpEntity(roomId, hedear);
		
			try {
				ResponseEntity<Object[]> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
						Object[].class);
				for (int i = 0; i < response.getBody().length; i++) {
					System.out.println("run--"+i);
					System.out.println("entity--"+response.getBody()[i]);
					System.out.println("run---------------"+i);
					list.add(response.getBody()[i]);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
//	public List<Object[]> getMessageContent(Long roomId) {
//		System.out.println("-123--" + roomId);
//		try {
//			String url = MessageAPI.API_GET_MESSAGE;
//			HttpHeaders hedear = new HttpHeaders();
//			hedear.setContentType(MediaType.APPLICATION_JSON);
//			HttpEntity<Object[]> httpEntity = new HttpEntity(roomId, hedear);
//			try {
//				ResponseEntity<Object[]> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
//						Object[].class);
//				System.out.println("--res--" + response.getBody());
////				List<Object[]> arr;
////				arr = (List<Object[]>) response.getBody();
//				for (int i = 0; i < response.getBody().length; i++) {
//					System.out.println("--"+response.getBody()[i]);
//				}
//				System.out.println("ba-"+(List<Object[]>) response);
//				return (List<Object[]>) response;
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		return null;
//	}
}
