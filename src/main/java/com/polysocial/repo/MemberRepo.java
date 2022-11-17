package com.polysocial.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polysocial.entity.Members;

@Repository
public interface MemberRepo extends JpaRepository<Members, Long> {

    List<Members> findByGroupId(Long groupId);
}
