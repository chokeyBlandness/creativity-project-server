package com.df.kaoyan.repository;

import com.df.kaoyan.dataobject.Clocked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClockedRepository extends JpaRepository<Clocked, Long> {
    List<Clocked> findClockedsByUserId(Long userId);

}
