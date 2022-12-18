package com.polysocial.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.xmlbeans.impl.jam.internal.elements.VoidClassImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.polysocial.entity.Notifications;

@Repository
public interface NotificationsRepo extends JpaRepository<Notifications, Long> {

    @Query("SELECT n FROM Notifications n WHERE n.user.userId = ?1")
    Page<Notifications> findByUserId(Long userId, Pageable pageable);

    @Query("SELECT n FROM Notifications n WHERE n.user.userId = ?1")
    List<Notifications> findByUserId(Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE Notifications n SET n.status = true WHERE n.user.userId = ?1 and n.notificationId = ?2")
    void updateOneStatus(Long userId, Long notiId);

    @Transactional
    @Modifying
    @Query("UPDATE Notifications n SET n.status = true WHERE n.user.userId = ?1")
    void updateAllStatus(Long userId);

}
