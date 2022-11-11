package com.polysocial.service;

import com.polysocial.dto.RegisterRequestDTO;
import com.polysocial.dto.UserDTO;

public interface LoginService {

    UserDTO loginWithEmailAddress(String email);

    UserDTO loginWithAccount(String email, String password);

    UserDTO registerNewUser(RegisterRequestDTO requestDTO);

}
