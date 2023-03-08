package com.example.xmlbasedconfig.controller;

import com.example.xmlbasedconfig.service.UserService;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        System.out.println("Controller initialized");
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
