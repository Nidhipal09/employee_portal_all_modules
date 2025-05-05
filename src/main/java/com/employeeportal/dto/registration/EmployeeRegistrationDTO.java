package com.employeeportal.dto.registration;

import java.sql.Date;
import lombok.Data;

@Data
public class EmployeeRegistrationDTO {

    private String fullName;
    private String email;
    private String mobileNo;
    private Date dob;
}