package com.employeeportal.dto.registration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationResponseDTO {

    private String email;
    private String fullName;
    private String mobileNumber;
    private String dateOfBirth;
    private String status;
}
