package com.polysocial.repo;

import com.polysocial.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepo extends JpaRepository<UserDetail, Long> {

    @Query("SELECT ud FROM UserDetail ud WHERE ud.user.userId =?1")
    UserDetail findByUserId(Long userId);
}
