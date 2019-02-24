package com.df.kaoyan.service.impl;

import com.df.kaoyan.dataobject.User;
import com.df.kaoyan.repository.UserInfoRepository;
import com.df.kaoyan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository repository;

    @Override
    public User findUserByEmail(String email){return repository.findUserByEmail(email);}

    @Override
    public void insertNewUser(User user) {
        repository.save(user);
    }

    @Override
    public void updateUser(User user){
        //only update userGender,targetProfession,targetSchool
        User foundUser = repository.findUserByUserId(user.getUserId());
        if (foundUser != null) {
            foundUser.setUserGender(user.getUserGender());
            foundUser.setTargetProfession(user.getTargetProfession());
            foundUser.setTargetSchool(user.getTargetSchool());
            repository.save(foundUser);
        }
    }

    @Override
    public User findUserByUserId(Long userId) {
        return repository.findUserByUserId(userId);
    }

}
