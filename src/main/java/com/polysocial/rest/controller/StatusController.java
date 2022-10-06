package com.polysocial.rest.controller;

import com.polysocial.consts.DemoAPI;
import com.polysocial.dto.ResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/status")
    public ResponseDTO getStatus(){
        ResponseDTO response = new ResponseDTO();
        response.setCode(200);
        response.setMessage("Running");
        return response;
    }
}
