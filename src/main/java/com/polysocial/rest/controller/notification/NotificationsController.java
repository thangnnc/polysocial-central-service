package com.polysocial.rest.controller.notification;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.service.notifications.NotificationsService;
import com.polysocial.config.jwt.JwtTokenProvider;
import com.polysocial.consts.*;

@CrossOrigin("*")
@RestController
public class NotificationsController {

    @Autowired
    private NotificationsService notificationsService;

    @Autowired
    private JwtTokenProvider jwt;

    @GetMapping(NotificationsAPI.API_NOTI_GET_ALL)
    public ResponseEntity getAllNotifications(@RequestHeader("Authorization") String token,
            @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        try {
            Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10));
            return ResponseEntity.ok(notificationsService.getAllNotifications(jwt.getIdFromJWT(token), pageable));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(NotificationsAPI.API_NOTI_UPDATE_ONE)
    public ResponseEntity updateOneStatus(@RequestHeader("Authorization") String token,
            @RequestParam("notiId") Long notiId) {
        try {
            return ResponseEntity.ok(notificationsService.updateOneStatus(jwt.getIdFromJWT(token), notiId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(NotificationsAPI.API_NOTI_UPDATE_ALL)
    public ResponseEntity updateAllStatus(@RequestHeader("Authorization") String token) {
        try {
            return ResponseEntity.ok(notificationsService.updateAllStatus(jwt.getIdFromJWT(token)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
