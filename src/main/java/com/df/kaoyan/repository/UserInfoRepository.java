package com.df.kaoyan.repository;

import com.df.kaoyan.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<User,Long> {
    User findUserByUserId(Long userId);

    User findUserByEmail(String email);

}
