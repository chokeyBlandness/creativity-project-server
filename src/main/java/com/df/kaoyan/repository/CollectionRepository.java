package com.df.kaoyan.repository;

import com.df.kaoyan.dataobject.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection,Long> {
    Collection findCollectionByPostIdAndUserId(Long postId, Long userId);

    List<Collection> findCollectionsByUserId(Long userId);
}
