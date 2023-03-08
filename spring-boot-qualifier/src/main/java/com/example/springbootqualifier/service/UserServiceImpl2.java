package com.example.springbootqualifier.service;

import com.example.springbootqualifier.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("ServiceImpl2")
public class UserServiceImpl2 implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl2(@Qualifier("RepositoryImpl2") UserRepository userRepository) {
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
    public void deleteUser() {
        userRepository.deleteUser();
    }

    @Override
    public void findUser() {
        userRepository.findUser();
    }
}
