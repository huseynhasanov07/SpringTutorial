package com.example.javabased.service;

import com.example.javabased.repository.UserRepository;

public class UserServiceImpl2 implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl2(UserRepository userRepository) {
        this.userRepository = userRepository;
        System.out.println("Second Service Impl initialized");
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
