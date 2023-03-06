package com.example.javabased.service;

import com.example.javabased.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl() {
        System.out.println("First Impl Service is initialized ");
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public void createUser() {
         userRepository.createUser();
    }

    @Override
    public void updateUser() {
         userRepository.updateUser();
    }

    @Override
    public void findUser() {
         userRepository.findUser();
    }

    @Override
    public void deleteUser() {
         userRepository.deleteUser();
    }
}
