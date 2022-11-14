package com.polysocial.service.impl.users;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.polysocial.dto.FriendDTO;
import com.polysocial.dto.UserDTO;
import com.polysocial.entity.Friends;
import com.polysocial.entity.Users;
import com.polysocial.repo.FriendRepo;
import com.polysocial.repo.UserRepo;
import com.polysocial.service.users.UserService;
import com.polysocial.utils.QrCode;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Autowired
    FriendRepo friendRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        List<Users> list = userRepo.findAll();
        List<UserDTO> listDTO = list.stream().map(element -> modelMapper.map(element, UserDTO.class)).collect(Collectors.toList());
        return listDTO;
    }

    @Override
    public UserDTO getOneUser(Long userId) {
        Users user = userRepo.findByUserId(userId);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public FriendDTO getUserFriend(Long userId, Long friendId) {
        Friends friend = friendRepo.getFriendByUserInviteIdAndUserConfirm(userId, friendId, friendId, userId);
        FriendDTO friendDTO = modelMapper.map(friend, FriendDTO.class);
        return friendDTO;
    }

    @Override
    public List<Friends> getAllFriend(Long userId) {
        List<Friends> list = friendRepo.getAllFriends(userId);
        return list;
    }

    @Override
    public List<UserDTO> searchUserByEmail(String email) {
        List<Users> list = userRepo.findByEmail(email);
        List<UserDTO> listDTO = list.stream().map(element -> modelMapper.map(element, UserDTO.class)).collect(Collectors.toList());
        return listDTO;
    }

    @Override
    public List<UserDTO> searchUserByName(String name) {
        List<Users> list = userRepo.findByFullName(name);
        List<UserDTO> listDTO = list.stream().map(element -> modelMapper.map(element, UserDTO.class)).collect(Collectors.toList());
        return listDTO;
    }


    
}
