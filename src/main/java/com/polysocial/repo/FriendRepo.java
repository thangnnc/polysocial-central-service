package com.polysocial.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.polysocial.entity.Friends;

@Repository
public interface FriendRepo extends JpaRepository<Friends, Long> {

    @Query("SELECT o FROM Friends o WHERE o.userInviteId =?1 or o.userInviteId =?2 AND o.userConfirmId =?3 or o.userConfirmId =?4")
    Friends getFriendByUserInviteIdAndUserConfirm(Long userInviteId, Long userInviteId2, Long userConfirmId,
            Long userConfirmId2);

    @Query("SELECT o FROM Friends o WHERE o.status = true and (o.userInviteId =?1 or o.userConfirmId =?1)")
    List<Friends> getAllFriends(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE Friends o SET o.status = 1 WHERE o.userInviteId =?1 and o.userConfirmId =?2")
    void acceptFriend(Long userInviteId, Long userConfirmId);

    @Modifying
    @Transactional
    @Query("DELETE Friends o WHERE o.userInviteId =?1 and o.userConfirmId =?2")
    void deleteRequestAddFriend(Long userInviteId, Long userConfirmId);

    @Query("SELECT o FROM Friends o WHERE o.status = false and o.userConfirmId =?1")
    List<Friends> getAllRequestAddFriend(Long userId);

    @Query("SELECT o FROM Friends o WHERE o.status = false and o.userInviteId =?1")
    List<Friends> getAllRequestAddFriendByUserInviteId(Long userId);

}