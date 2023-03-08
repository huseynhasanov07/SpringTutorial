package com.example.xmlbasedconfig.service;

import com.example.xmlbasedconfig.repository.UserRepository;

public class UserServiceImpl2 implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl2(UserRepository userRepository) {
        System.out.println("ServiceImpl2 initialized");
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
