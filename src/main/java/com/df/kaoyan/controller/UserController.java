package com.df.kaoyan.controller;

import com.alibaba.fastjson.JSON;
import com.df.kaoyan.dataobject.User;
import com.df.kaoyan.enums.ResultEnum;
import com.df.kaoyan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public @ResponseBody
    String login(@RequestBody String requestData){
        String loginMessage;
        User loginInfo = JSON.parseObject(requestData,User.class);
        User foundInfo = userService.findUserByEmail(loginInfo.getEmail());
        if (foundInfo == null){
            loginMessage = ResultEnum.LOGIN_ERROR.getMessage();
        }else {
            if (foundInfo.getPassword().equals(loginInfo.getPassword())){
                loginMessage = String.valueOf(foundInfo.getUserId());
            }else {
                loginMessage = ResultEnum.LOGIN_PSW_ERROR.getMessage();
            }
        }
        return JSON.toJSONString(loginMessage);
    }

    @PostMapping("/register")
    public @ResponseBody
    String registerMessage(@RequestBody String requestData){
        String registerMessage;
        User registerInfo = JSON.parseObject(requestData,User.class);
        User findInfo = userService.findUserByEmail(registerInfo.getEmail());
        if (findInfo != null){
            registerMessage = ResultEnum.ACCOUNT_EXIST.getMessage();
        }else {
            userService.insertNewUser(registerInfo);
            registerMessage = ResultEnum.REGISTER_SUCCESS.getMessage();
        }
        return JSON.toJSONString(registerMessage);
    }

    @PostMapping("/updateUserInfo")
    public @ResponseBody
    String updateUserInfo(@RequestBody String requestData){
        User updatedUser = JSON.parseObject(requestData, User.class);
        if (updatedUser == null||updatedUser.getUserId()==-1) {
            return JSON.toJSONString(ResultEnum.CHANGE_USER_INFO_FAILED.getMessage());
        } else {
            //todo only update userGender,targetProfession,targetSchool
            userService.updateUser(updatedUser);
            return JSON.toJSONString(ResultEnum.CHANGE_USER_INFO_SUCCESS.getMessage());
        }
    }

}
