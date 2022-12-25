package com.polysocial.repo;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polysocial.entity.Likes;

@Repository
public interface LikeRepo extends JpaRepository<Likes, Long>{

    List<Likes> findByPostId(Long postId);
    
}
