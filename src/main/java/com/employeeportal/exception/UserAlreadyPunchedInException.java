package com.employeeportal.exception;

public class UserAlreadyPunchedInException extends RuntimeException {
    public UserAlreadyPunchedInException(String message) {
        super(message);
    }
}