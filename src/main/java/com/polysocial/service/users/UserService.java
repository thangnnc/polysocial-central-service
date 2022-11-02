package com.polysocial.service.users;

import java.util.List;

import com.polysocial.dto.UserDTO;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getOneUser(Long userId);

    Boolean getUserFriend(Long userId, Long friendId);
    
}
