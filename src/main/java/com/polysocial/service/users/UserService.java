package com.polysocial.service.users;

import java.util.List;

import com.polysocial.dto.FriendDTO;
import com.polysocial.dto.UserDTO;
import com.polysocial.entity.Friends;
import com.polysocial.entity.Users;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getOneUser(Long userId);

    FriendDTO getUserFriend(Long userId, Long friendId);
    
    List<Friends> getAllFriend(Long userId);

    List<UserDTO> searchUserByEmail(String email);

    List<UserDTO> searchUserByName(String name);
}
