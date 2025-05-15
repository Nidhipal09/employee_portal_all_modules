package com.employeeportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MobileNumberAlreadyExistsException extends RuntimeException {
    public MobileNumberAlreadyExistsException() {
        super();
    }

    public MobileNumberAlreadyExistsException(String msg) {
        super(msg);
    }
}
