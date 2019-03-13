package com.df.kaoyan.service.impl;

import com.df.kaoyan.dataobject.Clocked;
import com.df.kaoyan.dataobject.User;
import com.df.kaoyan.repository.ClockedRepository;
import com.df.kaoyan.repository.UserInfoRepository;
import com.df.kaoyan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private ClockedRepository clockedRepository;

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

    @Override
    public List<Clocked> findClockedListByUserId(Long userId) {
        return clockedRepository.findClockedsByUserId(userId);
    }

    @Override
    public String createNewClocked(Clocked newClocked) {
        List<Clocked> foundClockedList = clockedRepository.findClockedsByUserId(newClocked.getUserId());
        for (Clocked foundClocked:foundClockedList){
            if (foundClocked.getClockInDate().getDate() == newClocked.getClockInDate().getDate()) {
                return "exited clocked";
            }
        }
        clockedRepository.save(newClocked);
        return "clocked successfully";
    }
}
