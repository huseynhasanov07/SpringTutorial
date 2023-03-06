package com.example.javabased.repository;

public class UserRepositoryImpl2 implements UserRepository {

    public UserRepositoryImpl2() {
        System.out.println("Second repositoryImpl initialized");
    }

    @Override
    public void createUser() {
        System.out.println("Second repositoryImpl created user");
    }

    @Override
    public void updateUser() {
        System.out.println("Second repositoryImpl updated user");
    }

    @Override
    public void findUser() {
        System.out.println("Second repositoryImpl found user");
    }

    @Override
    public void deleteUser() {
        System.out.println("Second repositoryImpl deleted user");
    }
}
