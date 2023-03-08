package com.example.springbootqualifier.repository;

import org.springframework.stereotype.Repository;

@Repository("RepositoryImpl1")
public class UserRepositoryImpl1 implements UserRepository {

    public UserRepositoryImpl1() {
        System.out.println("RepositoryImpl1 initialized");
    }

    @Override
    public void createUser() {
        System.out.println("RepositoryImpl1 created user");
    }

    @Override
    public void updateUser() {
        System.out.println("RepositoryImpl1 update user");
    }

    @Override
    public void deleteUser() {
        System.out.println("RepositoryImpl1 delete user");
    }

    @Override
    public void findUser() {
        System.out.println("RepositoryImpl1 found user");
    }
}
