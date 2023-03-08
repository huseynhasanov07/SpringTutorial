package com.example.xmlbasedconfig.repository;

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
        System.out.println("RepositoryImpl1 updated user");
    }

    @Override
    public void findUser() {
        System.out.println("RepositoryImpl1 found user");
    }

    @Override
    public void deleteUser() {
        System.out.println("RepositoryImpl1 deleted user");
    }
}
