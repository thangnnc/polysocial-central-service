package com.polysocial.rest.controller;

import com.polysocial.dto.LoginRequestDTO;
import com.polysocial.dto.RegisterRequestDTO;
import com.polysocial.dto.ResponseDTO;
import com.polysocial.dto.UserDTO;
import com.polysocial.service.LoginService;
import com.polysocial.utils.Logger;
import com.polysocial.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class LoginRestController {

    @Autowired
    private LoginService loginService;

    @PostMapping(path = "/api/login-email")
    public ResponseEntity loginWithEmail(@RequestBody LoginRequestDTO request){
        Logger.info("Start login with email api");
        UserDTO responseDTO = null;

        if (ValidateUtils.isNullOrEmpty(request.getEmail())){
            ResponseDTO response = new ResponseDTO();
            response.setStatus(HttpStatus.BAD_REQUEST);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }else {
            responseDTO = loginService.loginWithEmailAddress(request.getEmail());
        }

        if(ValidateUtils.isNullOrEmpty(responseDTO)){
            ResponseDTO response = new ResponseDTO();
            response.setStatus(HttpStatus.UNAUTHORIZED);
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }else{
            // Authorized
            return ResponseEntity.ok(responseDTO);
        }
    }

    @PostMapping(path = "/api/login-account")
    public ResponseEntity loginWithAccount(@RequestBody LoginRequestDTO request){
        Logger.info("Start login with account api");
        UserDTO responseDTO = null;

        if (ValidateUtils.isNullOrEmpty(request.getEmail()) && ValidateUtils.isNotNullOrEmpty(request.getPassword())){
            ResponseDTO response = new ResponseDTO();
            response.setStatus(HttpStatus.BAD_REQUEST);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }else {
            responseDTO = loginService.loginWithAccount(request.getEmail(), request.getPassword());
        }

        if(ValidateUtils.isNullOrEmpty(responseDTO)){
            ResponseDTO response = new ResponseDTO();
            response.setStatus(HttpStatus.UNAUTHORIZED);
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }else{
            // Authorized
            return ResponseEntity.ok(responseDTO);
        }
    }

    @PostMapping(path = "/api/register")
    public ResponseEntity doPostRegister(@RequestBody RegisterRequestDTO request ){
        Logger.info("Start register new user!");
        UserDTO userDTO = loginService.registerNewUser(request);
        if(ValidateUtils.isNullOrEmpty(userDTO)){
            ResponseDTO response = new ResponseDTO();
            response.setStatus(HttpStatus.BAD_REQUEST);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(userDTO);
    }
}
