package com.df.kaoyan.repository;

import com.df.kaoyan.dataobject.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentsByPostId(Long postId);

    void deleteCommentsByPostId(Long postId);
}
