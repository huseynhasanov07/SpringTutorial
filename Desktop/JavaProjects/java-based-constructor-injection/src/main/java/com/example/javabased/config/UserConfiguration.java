package com.example.javabased.config;

import com.example.javabased.controller.UserController;
import com.example.javabased.repository.UserRepository;
import com.example.javabased.repository.UserRepositoryImpl;
import com.example.javabased.repository.UserRepositoryImpl2;
import com.example.javabased.service.UserService;
import com.example.javabased.service.UserServiceImpl;
import com.example.javabased.service.UserServiceImpl2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    public UserController userController() {
        return new UserController(userService());
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl2(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl2();
    }

}
