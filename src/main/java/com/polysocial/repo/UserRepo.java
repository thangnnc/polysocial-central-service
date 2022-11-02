package com.polysocial.repo;

import com.polysocial.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

    Users findByEmailAndIsActive(String email, boolean isActive);

    Users findByUserIdAndIsActive(Long userId, boolean isActive);
    
    @Query("SELECT o FROM Users o WHERE o.email =?1")
    Users getUserByEmail(String email);

    
    
}
