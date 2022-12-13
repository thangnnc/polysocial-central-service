package com.polysocial.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.polysocial.entity.Contacts;

@Repository
public interface ContactRepo extends JpaRepository<Contacts, Long> {

    @Query("SELECT o FROM Contacts o WHERE o.roomId =?1")
    List<Contacts> getContactByRoomId(Long roomId);

    @Query("SELECT o FROM Contacts o WHERE o.user.userId =?1 and o.roomId =?2")
    List<Contacts> getContactByUserIdAndRoomIdContacts(Long userId, Long roomId);
    
}
