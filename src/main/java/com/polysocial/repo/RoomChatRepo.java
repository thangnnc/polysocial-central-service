package com.polysocial.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.polysocial.entity.RoomChats;

@Repository
public interface RoomChatRepo extends JpaRepository<RoomChats, Long> {

    @Query("SELECT o FROM RoomChats o WHERE o.group.groupId =?1")
    RoomChats getRoomChatByGroupId(Long groupId);
}
