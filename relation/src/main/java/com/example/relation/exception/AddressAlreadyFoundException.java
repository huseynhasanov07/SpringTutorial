package com.example.relation.exception;

import java.util.Date;

public class AddressAlreadyFoundException extends RuntimeException {



    public AddressAlreadyFoundException(String message) {
        super(message);
    }
}
