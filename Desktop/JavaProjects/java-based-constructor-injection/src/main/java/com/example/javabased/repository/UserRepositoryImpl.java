package com.example.javabased.repository;

public class UserRepositoryImpl implements UserRepository {

    public UserRepositoryImpl() {
        System.out.println("FirstImpl Repository is initialized");
    }

    @Override
    public void createUser() {
        System.out.println("First impl I created user");
    }

    @Override
    public void updateUser() {
        System.out.println("First impl I updated user");
    }

    @Override
    public void findUser() {
        System.out.println("First impl I find user");
    }

    @Override
    public void deleteUser() {
        System.out.println("First impl I deleted user");
    }
}
