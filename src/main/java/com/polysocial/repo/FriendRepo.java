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

    @Query("SELECT o FROM Friends o WHERE (o.userInvite.userId =?1 and o.userConfirm.userId =?2) or (o.userConfirm.userId =?1 and o.userInvite.userId =?2)")
    List<Friends> getFriendByUserInviteIdAndUserConfirm(Long userInviteId, Long userConfirm);

    @Query("SELECT o FROM Friends o WHERE o.status = true and (o.userInvite.userId =?1 or o.userConfirm.userId =?1)")
    List<Friends> getAllFriends(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE Friends o SET o.status = 1, o.isFriend = 1 WHERE o.userInvite.userId =?1 and o.userConfirm.userId =?2")
    void acceptFriend(Long userInviteId, Long userConfirmId);

    @Modifying
    @Transactional
    @Query("DELETE Friends o WHERE o.userInvite.userId =?1 and o.userConfirm.userId =?2 or o.userConfirm.userId =?1 and o.userInvite.userId =?2")
    void deleteRequestAddFriend(Long userInviteId, Long userConfirmId);

    @Query("SELECT o FROM Friends o WHERE o.status = false and o.userConfirm.userId =?1 order by o.createdDate desc")
    List<Friends> getAllRequestAddFriend(Long userId);

    @Query("SELECT o FROM Friends o WHERE o.status = false and o.userInvite.userId =?1")
    List<Friends> getAllRequestAddFriendByUserInviteId(Long userId);

    @Query("SELECT o FROM Friends o WHERE o.userConfirm.userId =?1 and o.userInvite.userId =?2 or o.userConfirm.userId =?2 and o.userInvite.userId =?1")
    List<Friends> getGroupByUser(Long userInviteId, Long userConfirmId);

    @Query("SELECT o FROM Friends o WHERE o.userInvite.userId =?1 and o.userConfirm.userId =?2")
    Friends findFriendByUserInviteIdAndUserConfirmId(Long userInviteId, Long userConfirmId);

    @Query("SELECT o FROM Friends o WHERE o.userConfirm.userId =?1 and o.userInvite.userId =?2")
    Friends findFriendByUserConfirmIdAndUserInviteId(Long userConfirmId, Long userInviteId);

}