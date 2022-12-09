package com.polysocial.rest.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.consts.UserAPI;
import com.polysocial.dto.ResponseDTO;
import com.polysocial.dto.UserDetailDTO;
import com.polysocial.service.users.UserDetailService;

@RestController
@CrossOrigin("*")
public class UserDetaiilController {
    
    @Autowired
    private UserDetailService userDetailService;

    @PutMapping(UserAPI.API_UPDATE_USER_DETAILS)
    public ResponseEntity updateUserDetail(@RequestBody UserDetailDTO userDetailDTO){
        try{
            return ResponseEntity.ok(userDetailService.updateUserDetail(userDetailDTO));
        }catch(Exception e){
            e.printStackTrace();
            ResponseDTO response = new ResponseDTO();
            response.setStatus(HttpStatus.BAD_REQUEST);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(UserAPI.API_GET_ALL_USER_DETAILS)
    public ResponseEntity getAllUserDetail(){
        try{
            return ResponseEntity.ok(userDetailService.getAllUserDetail());
        }catch(Exception e){
            e.printStackTrace();
            ResponseDTO response = new ResponseDTO();
            response.setStatus(HttpStatus.BAD_REQUEST);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }
}
