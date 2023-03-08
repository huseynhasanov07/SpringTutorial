package com.example.springbootqualifier.service;

import com.example.springbootqualifier.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("ServiceImpl1")
public class UserServiceImpl1 implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl1(@Qualifier("RepositoryImpl1") UserRepository userRepository) {
        this.userRepository = userRepository;
        System.out.println("ServiceImpl1 initialized");
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
    public void deleteUser() {
        userRepository.deleteUser();
    }

    @Override
    public void findUser() {
        userRepository.findUser();
    }
}
