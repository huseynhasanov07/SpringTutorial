package com.example.xmlbasedconfig.service;

import com.example.xmlbasedconfig.repository.UserRepository;

public class UserServiceImpl1 implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl1(UserRepository userRepository) {
        System.out.println("ServiceImpl1 initialized");
        this.userRepository = userRepository;
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
