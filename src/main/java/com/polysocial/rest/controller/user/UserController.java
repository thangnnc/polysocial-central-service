package com.polysocial.rest.controller.user;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.consts.UserAPI;
import com.polysocial.dto.FriendDTO;
import com.polysocial.dto.FriendDetailDTO;
import com.polysocial.dto.UserDTO;
import com.polysocial.entity.Friends;
import com.polysocial.repo.FriendRepo;
import com.polysocial.service.users.UserService;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    FriendRepo friendRepo;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping(UserAPI.API_GET_ALL_USER)
    public ResponseEntity getAllUser() {
        try {
            List<UserDTO> listDTO = userService.getAllUsers();
            return ResponseEntity.ok(listDTO);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(UserAPI.API_GET_ONE_USER)
    public ResponseEntity getOneUser(@RequestParam Long userId) {
        try {
            UserDTO userDTO = userService.getOneUser(userId);
          
            return ResponseEntity.ok(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(UserAPI.API_GET_FRIEND)
    public ResponseEntity getFriend(@RequestParam Long userId, @RequestParam Long friendId) {
        try {
            FriendDTO friend = userService.getUserFriend(userId, friendId);
            return ResponseEntity.ok(friend);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(UserAPI.API_GET_ALL_FRIEND)
    public ResponseEntity getAllFriends(@RequestParam Long userId){
        try{
            List<FriendDetailDTO> list = userService.getAllFriend(userId);
            return ResponseEntity.ok(list);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(UserAPI.API_SEARCH_USER_BY_EMAIL)
    public ResponseEntity searchUserByEmail(@RequestParam String email){
        try{
            List<UserDTO> list = userService.searchUserByEmail(email);
            return ResponseEntity.ok(list);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(UserAPI.API_SEARCH_USER_BY_NAME)
    public ResponseEntity searchUserByName(@RequestParam String name){
        try{
            List<UserDTO> list = userService.searchUserByName(name);
            return ResponseEntity.ok(list);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(UserAPI.API_ADD_FRIEND)
    public ResponseEntity addFriend(@RequestBody FriendDTO friendDTO) {
        try {
            FriendDetailDTO friend = userService.addFriend(friendDTO.getUserConfirmId(), friendDTO.getUserInviteId());
            return ResponseEntity.ok(friend);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(UserAPI.API_ACCEPT_FRIEND)
    public ResponseEntity acceptFriend(@RequestBody FriendDTO friendDTO) {
        try {
            FriendDetailDTO friend = userService.acceptFriend(friendDTO.getUserConfirmId(), friendDTO.getUserInviteId());
            return ResponseEntity.ok(friend);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(UserAPI.API_DELETE_REQUEST_ADD_FRIEND)
    public ResponseEntity rejectFriend(@RequestBody FriendDTO friendDTO) {
        try {
            userService.deleteRequestAddFriend(friendDTO.getUserConfirmId(), friendDTO.getUserInviteId());
            return ResponseEntity.ok("Delete success");
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(UserAPI.API_GET_ALL_REQUEST_ADD_FRIEND)
    public ResponseEntity getAllFriendRequest(@RequestParam Long userId){
        try{
            List<FriendDetailDTO> list = userService.getAllRequestAddFriend(userId);
            return ResponseEntity.ok(list);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
