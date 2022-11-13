package com.polysocial.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.polysocial.entity.Friends;

@Repository
public interface FriendRepo extends JpaRepository<Friends, Long> {

    @Query("SELECT o FROM Friends o WHERE o.userInviteId =?1 or o.userInviteId =?2 AND o.userConfirmId =?3 or o.userConfirmId =?4")
    Friends getFriendByUserInviteIdAndUserConfirm(Long userInviteId, Long userInviteId2, Long userConfirmId, Long userConfirmId2);

    @Query("SELECT o FROM Friends o WHERE o.status = 1 and (o.userInviteId =?1 or o.userConfirmId =?1)")
    List<Friends> getAllFriends(Long userId);
}
    
import com.polysocial.entity.Friends;
import com.polysocial.entity.id.FriendId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepo extends JpaRepository<Friends, FriendId> {
}
