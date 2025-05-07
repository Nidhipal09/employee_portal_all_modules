package com.employeeportal.dto.registration;

import java.sql.Date;
import lombok.Data;

@Data
public class RegistrationRequestDTO {

    private String fullName;
    private String email;
    private String mobileNumber;
    private Date dateOfBirth;
}