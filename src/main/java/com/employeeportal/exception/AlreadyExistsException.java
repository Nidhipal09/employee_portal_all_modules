package com.employeeportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException() {
        super();
    }

    public AlreadyExistsException(String msg) {
        super(msg);
    }
}
