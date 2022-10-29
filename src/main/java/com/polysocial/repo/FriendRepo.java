package com.polysocial.repo;

import com.polysocial.entity.Friends;
import com.polysocial.entity.id.FriendId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepo extends JpaRepository<Friends, FriendId> {
}
