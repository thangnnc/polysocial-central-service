package com.polysocial.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polysocial.entity.Posts;

public interface PostRepo extends JpaRepository<Posts, Long>{
    
    Posts findByPostId(Long postId);
}
