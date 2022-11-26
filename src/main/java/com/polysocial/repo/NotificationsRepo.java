package com.polysocial.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.polysocial.entity.Notifications;

@Repository
public interface NotificationsRepo extends JpaRepository<Notifications, Long> {

    @Query("SELECT n FROM Notifications n WHERE n.user.userId = ?1")
    List<Notifications> findByUserId(Long userId);


}
