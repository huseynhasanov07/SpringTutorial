package com.example.relation.controller;

import com.example.relation.dto.AddressRequest;
import com.example.relation.dto.AddressResponse;
import com.example.relation.dto.UserRequest;
import com.example.relation.dto.UserResponse;
import com.example.relation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${rest.api.url}")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        return new ResponseEntity<>(service.createUser(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        service.deleteUser(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest request, @RequestParam String street) {
        return new ResponseEntity<>(service.updateUser(id, request, street), HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<UserResponse> updateUserByName(@PathVariable Long id, @RequestBody UserRequest request) {
        return new ResponseEntity<>(service.updateUserByName(id, request.getName()), HttpStatus.OK);
    }


    @PostMapping("/addresses/{id}")
    public ResponseEntity<List<AddressResponse>> createAddresses(@PathVariable Long id, @RequestBody List<AddressRequest> addresses) {
        return new ResponseEntity<>(service.createAddresses(id, addresses), HttpStatus.CREATED);
    }

    @GetMapping("/id")
    public ResponseEntity<AddressResponse> getAddressById(@RequestParam Long id) {
        return new ResponseEntity<>(service.findAddressById(id), HttpStatus.FOUND);
    }

    @DeleteMapping("/id")
    public void deleteAddress(@RequestParam Long id) {
        service.deleteAddress(id);
    }

    //
    @PutMapping("/id")
    public ResponseEntity<AddressResponse> updateAddress(@RequestParam("id") Long id, @RequestBody AddressRequest request) {
        return new ResponseEntity<>(service.updateAddress(id, request), HttpStatus.OK);
    }


}
