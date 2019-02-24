package com.df.kaoyan.repository;


import com.df.kaoyan.dataobject.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findPostsByUserId(Long userId);

    Post findPostByPostId(Long postId);
}
