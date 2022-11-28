package com.polysocial.service.impl.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.polysocial.consts.ContactAPI;
import com.polysocial.dto.ContactDTO;
import com.polysocial.service.contact.ContactService;

@Service
public class ContactServiceImpl implements ContactService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public ContactDTO createContact(ContactDTO dto) {
		try {
			String url = ContactAPI.API_CREATE_CONTACT;
			HttpHeaders hedear = new HttpHeaders();
			hedear.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<ContactDTO> httpEntity = new HttpEntity(dto,hedear);
			ResponseEntity<ContactDTO> entity = restTemplate.exchange(url, HttpMethod.POST,httpEntity,ContactDTO.class);
			return entity.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
