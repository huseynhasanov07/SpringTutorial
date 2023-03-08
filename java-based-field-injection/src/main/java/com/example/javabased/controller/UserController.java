package com.example.javabased.controller;

import com.example.javabased.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserController {
    @Autowired
    private UserService userService;

    public UserController() {
        System.out.println("Controller is initialized");
    }

    public void createUser() {
         userService.createUser();
    }

    public void updateUser() {
         userService.updateUser();
    }


    public void findUser() {
         userService.findUser();
    }

    public void deleteUser() {
         userService.deleteUser();
    }

}
