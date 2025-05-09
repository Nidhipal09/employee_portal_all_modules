package com.employeeportal.dto.onboarding;

import lombok.Data;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class EmployeeDTO {
    private int employeeId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mobileNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
    private String email;
}