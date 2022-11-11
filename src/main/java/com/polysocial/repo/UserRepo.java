package com.polysocial.repo;

import com.polysocial.dto.UserDTO;
import com.polysocial.entity.Users;

import java.util.List;

import org.apache.tomcat.util.log.UserDataHelper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

    Users findByEmailAndIsActive(String email, boolean isActive);

    Users findByUserIdAndIsActive(Long userId, boolean isActive);

    Users findByUserId(Long userId);

    @Query("SELECT o FROM Users o WHERE o.email Like %?1%")
    List<Users> findByEmail(String email);

    @Query("SELECT o FROM Users o WHERE o.fullName Like %?1%")
    List<Users> findByFullName(String fullName);

    
}
