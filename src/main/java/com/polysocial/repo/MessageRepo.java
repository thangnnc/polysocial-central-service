package com.polysocial.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polysocial.entity.Messages;

@Repository
public interface MessageRepo extends JpaRepository<Messages, Long> {

    
}
