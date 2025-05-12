package com.employeeportal.model.registration;

import lombok.Data;

@Data
public class ValidateOtpTokenResponse {
    private String email;
    private String token;
}
