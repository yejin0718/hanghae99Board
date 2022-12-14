package com.board.board.like.repository;

import com.board.board.like.entity.LikeComment;
import com.board.board.like.entity.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikePostRepository extends JpaRepository<LikePost, Long> {
    Boolean existsByPostIdAndUsername(Long id, String username);

    Optional<LikePost> findByPostIdAndUsername(Long id, String username);
}
