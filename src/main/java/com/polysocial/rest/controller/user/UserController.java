package com.polysocial.rest.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.repo.FriendRepo;
import com.polysocial.repo.UserRepo;

@RestController
public class UserController {
    
    @Autowired
    UserRepo userRepo;

    @Autowired
    FriendRepo friendRepo;
    
    @GetMapping("/test/user")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(friendRepo.getFriendByUserInviteIdAndUserConfirm(1L, 2L));
    }
}
