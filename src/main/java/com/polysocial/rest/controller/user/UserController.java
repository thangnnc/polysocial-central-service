package com.polysocial.rest.controller.user;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.polysocial.consts.UserAPI;
import com.polysocial.dto.FriendDTO;
import com.polysocial.dto.UserDTO;
import com.polysocial.entity.Friends;
import com.polysocial.entity.Users;
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
            return null;
        }
    }

    @GetMapping(UserAPI.API_GET_ONE_USER)
    public ResponseEntity getOneUser(@RequestParam Long userId) {
        try {
            UserDTO userDTO = userService.getOneUser(userId);
          
            return ResponseEntity.ok(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(UserAPI.API_GET_FRIEND)
    public ResponseEntity getFriend(@RequestParam Long userId, @RequestParam Long friendId) {
        try {
            FriendDTO friend = userService.getUserFriend(userId, friendId);
            return ResponseEntity.ok(friend);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(UserAPI.API_GET_ALL_FRIEND)
    public ResponseEntity getAllFriends(@RequestParam Long userId){
        try{
            List<Friends> list = userService.getAllFriend(userId);
            return ResponseEntity.ok(list);
        }catch(Exception e){
            return null;
        }
    }
    @GetMapping(UserAPI.API_SEARCH_USER_BY_EMAIL)
    public ResponseEntity searchUserByEmail(@RequestParam String email){
        try{
            List<UserDTO> list = userService.searchUserByEmail(email);
            return ResponseEntity.ok(list);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(UserAPI.API_SEARCH_USER_BY_NAME)
    public ResponseEntity searchUserByName(@RequestParam String name){
        try{
            List<UserDTO> list = userService.searchUserByName(name);
            return ResponseEntity.ok(list);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
