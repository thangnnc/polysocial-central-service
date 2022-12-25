package com.polysocial.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.polysocial.entity.Comments;

public interface CommentRepo  extends JpaRepository<Comments, Long>{
    
}
