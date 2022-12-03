package com.polysocial.service.impl.ViewedStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.polysocial.consts.ViewedStatusAPI;
import com.polysocial.dto.ViewedStatusDTO;
import com.polysocial.service.ViewedStatus.ViewedStatusService;

@Service
public class ViewedStatusServiceImpl implements ViewedStatusService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public ViewedStatusDTO updateViewedStatusDTO(ViewedStatusDTO dto) {
		try {
			String url = ViewedStatusAPI.API_UPDATE_VIEWEDSTATUS;
			HttpHeaders hedear = new HttpHeaders();
			hedear.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<ViewedStatusDTO> httpEntity = new HttpEntity(dto, hedear);
			ResponseEntity<ViewedStatusDTO> entity= restTemplate.exchange(url, HttpMethod.POST, httpEntity,ViewedStatusDTO.class);
			return entity.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
