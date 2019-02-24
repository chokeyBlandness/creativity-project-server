package com.df.kaoyan.service;

import com.df.kaoyan.dataobject.User;

public interface UserService {
    User findUserByEmail(String email);

    void insertNewUser(User user);

    void updateUser(User user);

    User findUserByUserId(Long userId);
}
