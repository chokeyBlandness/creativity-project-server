package com.df.kaoyan.service;

import com.df.kaoyan.dataobject.Clocked;
import com.df.kaoyan.dataobject.User;

import java.util.List;

public interface UserService {
    User findUserByEmail(String email);

    void insertNewUser(User user);

    void updateUser(User user);

    User findUserByUserId(Long userId);

    List<Clocked> findClockedListByUserId(Long userId);

    String createNewClocked(Clocked newClocked);
}
