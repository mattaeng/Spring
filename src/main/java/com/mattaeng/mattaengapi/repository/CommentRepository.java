package com.mattaeng.mattaengapi.repository;

import com.mattaeng.mattaengapi.entity.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByFeed_Id(Long id);
    Optional<Comment> findByFeed_IdAndId(Long feed_id, Long id);
}
