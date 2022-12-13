package com.polysocial.rest.controller.accessTime;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.config.jwt.JwtTokenProvider;
import com.polysocial.consts.AccessTimeAPI;
import com.polysocial.service.accessTime.AccessTimeService;

@RestController
@CrossOrigin(origins = "*")
public class AccessTimeController {
    
    @Autowired
    private AccessTimeService accessTimeService;

    @Autowired
    JwtTokenProvider jwt;

    @PostMapping(AccessTimeAPI.ACCESS_TIME_CREATE)
    public ResponseEntity createAccessTime(@RequestHeader("Authorization") String token){
        try{
            return ResponseEntity.ok(accessTimeService.createAccsessTime(jwt.getIdFromJWT(token)));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }   
    }

    @GetMapping(AccessTimeAPI.ACCESS_TIME_GET_ALL)
    public ResponseEntity getAllAccessTime(){
        try{
            return ResponseEntity.ok(accessTimeService.getAllAccessTime());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(AccessTimeAPI.ACCESS_TIME_STATISTICAL)
    public ResponseEntity getStatisticalAccessTime(@RequestParam(value = "years") Optional<Integer> year, @RequestParam("monthFirst") Optional<Integer> monthFirst, @RequestParam("monthLast") Optional<Integer> monthLast){
        try{
            return ResponseEntity.ok(accessTimeService.getAccessTimeStatistical(year.orElse(0), monthFirst.orElse(0), monthLast.orElse(0)));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
