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
    
    @Query("SELECT o FROM Users o WHERE o.email =?1")
    Users getUserByEmail(String email);

    @Query("SELECT o.userId, o.fullName, o.studentCode, o.email, o.avatar From Users o")
    List<Users> getAllUsers();

    @Query("SELECT o.userId, o.fullName, o.studentCode, o.email, o.avatar From Users o WHERE o.userId =?1")
    Users getOneUser(Long userId);

    @Query("SELECT o FROM Users o WHERE o.email LIKE %?1%")
    List<Users> searchUserByEmail(String email);

    @Query("SELECT o FROM Users o WHERE o.fullName LIKE %?1%")
    List<Users> searchUserByName(String name);
}
