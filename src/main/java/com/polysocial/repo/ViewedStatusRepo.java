package com.polysocial.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polysocial.entity.ViewedStatus;


@Repository
public interface ViewedStatusRepo  extends JpaRepository<ViewedStatus, Long> {

    
}
