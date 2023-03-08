package com.example.springbootqualifier.repository;

import org.springframework.stereotype.Repository;

@Repository("RepositoryImpl2")
public class UserRepositoryImpl2 implements UserRepository {

    public UserRepositoryImpl2() {
        System.out.println("RepositoryImpl2 initialized");
    }

    @Override
    public void createUser() {
        System.out.println("RepositoryImpl2 created user");
    }

    @Override
    public void updateUser() {
        System.out.println("RepositoryImpl2 update user");
    }

    @Override
    public void deleteUser() {
        System.out.println("RepositoryImpl2 delete user");
    }

    @Override
    public void findUser() {
        System.out.println("RepositoryImpl2 found user");
    }
}
