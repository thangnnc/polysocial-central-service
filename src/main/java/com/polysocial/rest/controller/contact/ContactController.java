package com.polysocial.rest.controller.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.consts.CentralAPI;
import com.polysocial.dto.ContactDTO;
import com.polysocial.dto.ResponseDTO;
import com.polysocial.service.contact.ContactService;
import com.polysocial.utils.ValidateUtils;

@CrossOrigin("*")
@RestController
public class ContactController {

	@Autowired
	ContactService contactService;
	
	@PostMapping(CentralAPI.CREATE_CONTACT)
	public ResponseEntity create (@RequestBody ContactDTO request) {
		if (ValidateUtils.isNullOrEmpty(request.getUserId())) {
			ResponseDTO response = new ResponseDTO();
			response.setStatus(HttpStatus.BAD_REQUEST);
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		} else {
			ContactDTO response = contactService.createContact(request);
			return ResponseEntity.ok(response);
		}
	}
}
