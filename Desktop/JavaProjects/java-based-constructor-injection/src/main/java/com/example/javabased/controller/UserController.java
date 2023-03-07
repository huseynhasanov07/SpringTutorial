package com.example.javabased.controller;

import com.example.javabased.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        System.out.println("Controller is initialized");
        this.userService = userService;
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
