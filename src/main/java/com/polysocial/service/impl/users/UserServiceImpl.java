package com.polysocial.service.impl.users;

import java.util.List;

import org.springframework.stereotype.Service;

import com.polysocial.dto.UserDTO;
import com.polysocial.service.users.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public List<UserDTO> getAllUsers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserDTO getOneUser(Long userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean getUserFriend(Long userId, Long friendId) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
