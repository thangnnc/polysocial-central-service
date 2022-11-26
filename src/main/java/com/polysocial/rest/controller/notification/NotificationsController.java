package com.polysocial.rest.controller.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.service.notifications.NotificationsService;
import com.polysocial.config.jwt.JwtTokenProvider;
import com.polysocial.consts.*;

@RestController
public class NotificationsController {
    
    @Autowired
    private NotificationsService notificationsService;

    @Autowired
    private JwtTokenProvider jwt;

    @GetMapping(NotificationsAPI.API_NOTI_GET_ALL)
    public ResponseEntity getAllNotifications(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(notificationsService.getAllNotifications(jwt.getIdFromJWT(token)));
    }
}
