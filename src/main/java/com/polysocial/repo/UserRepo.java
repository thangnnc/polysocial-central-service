package com.polysocial.repo;

import com.polysocial.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

    Users findByEmailAndIsActive(String email, boolean isActive);
}
