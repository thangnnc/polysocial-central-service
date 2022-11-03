package com.polysocial.service.impl.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polysocial.dto.FriendDTO;
import com.polysocial.dto.UserDTO;
import com.polysocial.entity.Friends;
import com.polysocial.entity.Users;
import com.polysocial.repo.FriendRepo;
import com.polysocial.repo.UserRepo;
import com.polysocial.service.users.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Autowired
    FriendRepo friendRepo;

    @Override
    public List<Users> getAllUsers() {
        List<Users> list = userRepo.getAllUsers();
        return list;
    }

    @Override
    public Users getOneUser(Long userId) {
        Users user = userRepo.getOneUser(userId);
        return user;
    }

    @Override
    public Friends getUserFriend(Long userId, Long friendId) {
        Friends friend = friendRepo.getFriendByUserInviteIdAndUserConfirm(userId, friendId, friendId, userId);
        return friend;
    }

    @Override
    public List<Friends> getAllFriend(Long userId) {
        List<Friends> list = friendRepo.getAllFriends(userId);
        return list;
    }

    @Override
    public List<Users> searchUserByEmail(String email) {
        List<Users> list = userRepo.searchUserByEmail(email);
        return list;
    }

    @Override
    public List<Users> searchUserByName(String name) {
        List<Users> list = userRepo.searchUserByName(name);
        return list;
    }
    
}
