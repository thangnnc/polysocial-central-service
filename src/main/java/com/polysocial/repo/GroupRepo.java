package com.polysocial.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.polysocial.entity.Groups;

@Repository
public interface GroupRepo extends JpaRepository<Groups,Long>{
    
    @Query("SELECT o FROM Groups o WHERE o.name =?1")
    Groups findGroupByName(String name);
}
