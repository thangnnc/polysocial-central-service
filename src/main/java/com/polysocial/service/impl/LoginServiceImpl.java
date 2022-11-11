package com.polysocial.service.impl;

import com.polysocial.config.jwt.JwtTokenProvider;
import com.polysocial.config.security.CustomUserDetails;
import com.polysocial.dto.RegisterRequestDTO;
import com.polysocial.dto.UserDTO;
import com.polysocial.entity.Roles;
import com.polysocial.entity.UserDetail;
import com.polysocial.entity.Users;
import com.polysocial.repo.RoleRepo;
import com.polysocial.repo.UserRepo;
import com.polysocial.service.LoginService;
import com.polysocial.utils.Logger;
import com.polysocial.utils.ValidateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    @Override
    public UserDTO loginWithEmailAddress(String email) {
        UserDTO responseDTO = null;
        Users user = userRepo.findByEmailAndIsActive(email, true);
        if (ValidateUtils.isNotNullOrEmpty(user)){
            responseDTO = modelMapper.map(user, UserDTO.class);
            responseDTO.setPassword(null);
            responseDTO.setToken(tokenProvider.generateToken(new CustomUserDetails(user)));
        }
        return responseDTO;
    }

    @Override
    public UserDTO loginWithAccount(String email, String password) {
        UserDTO responseDTO = null;
        Users user = userRepo.findByEmailAndIsActive(email, true);
        if (ValidateUtils.isNotNullOrEmpty(user) && bCrypt.matches(password, user.getPassword())){
            responseDTO = modelMapper.map(user, UserDTO.class);
            responseDTO.setPassword(null);
            responseDTO.setToken(tokenProvider.generateToken(new CustomUserDetails(user)));
        }
        return responseDTO;
    }

    @Override
    public UserDTO registerNewUser(RegisterRequestDTO requestDTO) {
        try {
            if (ValidateUtils.isNotNullOrEmpty(userRepo.findByEmailAndIsActive(requestDTO.getEmail(),true))) {
                return null;
            }
            BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
            Users user = modelMapper.map(requestDTO, Users.class);
            user.setPassword(bCrypt.encode(user.getPassword()));
            user.setActive(true);
            UserDetail userDetail = modelMapper.map(requestDTO, UserDetail.class);

            Roles role = roleRepo.findByName(requestDTO.getRole());
            if(ValidateUtils.isNullOrEmpty(role)){
                return null;
            }
            user.setRoleId(role.getRoleId());
            user.setUserDetail(userDetail);
            user.setCreatedDate(LocalDateTime.now());
            userDetail.setUser(user);

            user = userRepo.save(user);
            UserDTO response = modelMapper.map(user, UserDTO.class);
            response.setPassword(null);
            response.setToken(tokenProvider.generateToken(new CustomUserDetails(user)));
            return response;
        } catch (Exception e){
            Logger.error("Error register new user: " + e.getMessage());
            return null;
        }
    }
}
