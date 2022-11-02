package com.polysocial.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.polysocial.dto.FriendDTO;
import com.polysocial.entity.Friends;

@Repository
public interface FriendRepo extends JpaRepository<Friends, Long> {

    @Query("SELECT o FROM Friends o WHERE o.userInviteId =?1 AND o.userConfirmId =?2")
    Friends getFriendByUserInviteIdAndUserConfirm(Long userInviteId, Long userConfirmId);
}
    
