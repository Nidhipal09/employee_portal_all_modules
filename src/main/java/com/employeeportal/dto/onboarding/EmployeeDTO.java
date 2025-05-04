package com.employeeportal.dto.onboarding;

import lombok.Data;
import java.sql.Date;

@Data
public class EmployeeDTO {
    private int employeeId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mobileNumber;
    private Date dateOfBirth;
    private String email;
    private String password;
    private String status;
}