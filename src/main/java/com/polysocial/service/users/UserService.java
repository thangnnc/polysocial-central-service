package com.polysocial.service.users;

import java.util.List;

import com.polysocial.dto.FriendDTO;
import com.polysocial.dto.UserDTO;
import com.polysocial.entity.Friends;
import com.polysocial.entity.Users;

public interface UserService {
    List<Users> getAllUsers();

    Users getOneUser(Long userId);

    Friends getUserFriend(Long userId, Long friendId);
    
    List<Friends> getAllFriend(Long userId);

    List<Users> searchUserByEmail(String email);

    List<Users> searchUserByName(String name);
}
