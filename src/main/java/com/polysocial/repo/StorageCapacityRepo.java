package com.polysocial.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.polysocial.entity.StorageCapacity;

@Repository
public interface StorageCapacityRepo extends JpaRepository<StorageCapacity, Long> {

    @Query("SELECT o FROM StorageCapacity o WHERE o.user.userId =?1")
    StorageCapacity findByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE StorageCapacity o SET o.capacity = ?1 WHERE o.user.userId = ?2")
    void updateCapacity(Long capacity, Long userId);

}
