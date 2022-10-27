package com.polysocial.config.security;

import com.polysocial.entity.Users;
import com.polysocial.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userId) {
        Long userCode = Long.parseLong(userId);
        Users user = userRepo.findByUserIdAndIsActive(userCode, true);
        if (user == null) {
            throw new UsernameNotFoundException(userCode.toString());
        }
        return new CustomUserDetails(user);
    }
}
