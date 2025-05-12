package com.employeeportal.model;

import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class LoginRequest {

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Email must be a valid email address")
    private String email;
    
    private String password;
}

