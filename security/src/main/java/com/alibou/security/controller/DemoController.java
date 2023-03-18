package com.alibou.security.controller;

import com.alibou.security.model.User;
import com.alibou.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/demo-controller")
@RequiredArgsConstructor
public class DemoController {

    private final UserRepository repository;


    @GetMapping("{id}")
    public ResponseEntity<User> getUserCredentials(@PathVariable Integer id) {
        User user = repository.findById(id).orElseThrow(() -> {
            throw new UsernameNotFoundException("Username not found email");
        });
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.FOUND);
    }


}
