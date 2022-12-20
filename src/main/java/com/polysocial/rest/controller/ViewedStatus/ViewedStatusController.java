package com.polysocial.rest.controller.ViewedStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.consts.CentralAPI;
import com.polysocial.dto.ResponseDTO;
import com.polysocial.dto.ViewedStatusDTO;
import com.polysocial.service.ViewedStatus.ViewedStatusService;
import com.polysocial.utils.ValidateUtils;

@CrossOrigin("*")
@RestController
public class ViewedStatusController {
	
	@Autowired
	private ViewedStatusService viewedStatusService;
	
	@PostMapping(CentralAPI.UPDATE_VIEW_STATUS)
	public ResponseEntity updateviewedStatus(@RequestBody ViewedStatusDTO dto) {
		if (ValidateUtils.isNullOrEmpty(dto.getContactId())) {
			ResponseDTO response = new ResponseDTO();
			response.setStatus(HttpStatus.BAD_REQUEST);
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		} else {
			return ResponseEntity.ok(viewedStatusService.updateViewedStatusDTO(dto));
		}
	}
	
	@PostMapping(CentralAPI.UPDATE_All_VIEW_STATUS)
	public ResponseEntity updateAllviewedStatus(@RequestBody ViewedStatusDTO dto) {
		if (ValidateUtils.isNullOrEmpty(dto.getUserId())) {
			ResponseDTO response = new ResponseDTO();
			response.setStatus(HttpStatus.BAD_REQUEST);
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		} else {
			return ResponseEntity.ok(viewedStatusService.updateAllViewedStatusDTO(dto));
		}
	}
}
