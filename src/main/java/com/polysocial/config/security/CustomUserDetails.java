package com.polysocial.config.security;

import com.polysocial.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    Users user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(user.getRole().getName().equalsIgnoreCase("STUDENT")){
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_STUDENT"));
        }else if(user.getRole().getName().equalsIgnoreCase("TEACHER")){
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_TEACHER"));
        }else {
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_MANAGER"));
        }
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
