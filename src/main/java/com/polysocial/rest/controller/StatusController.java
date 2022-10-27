package com.polysocial.rest.controller;

import com.polysocial.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/status")
    public ResponseDTO getStatus(){
        ResponseDTO response = new ResponseDTO();
        response.setStatus(HttpStatus.OK);
        return response;
    }
}
