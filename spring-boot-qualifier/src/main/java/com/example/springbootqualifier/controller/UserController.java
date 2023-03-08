package com.example.springbootqualifier.controller;

import com.example.springbootqualifier.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(@Qualifier("ServiceImpl2") UserService userService) {
        System.out.println("Controller initialized");
        this.userService = userService;
    }

    @Bean
    public void createUser() {
        System.out.println("I am controller");
        userService.createUser();
    }

    public void updateUser() {
        userService.updateUser();
    }

    public void deleteUser() {
        userService.deleteUser();
    }

    public void findUser() {
        userService.findUser();
    }


}
