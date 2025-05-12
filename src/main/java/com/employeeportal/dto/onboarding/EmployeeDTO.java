package com.employeeportal.dto.onboarding;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@AllArgsConstructor
public class EmployeeDTO {
    private String fullName;
    private String mobileNumber;
    private String dateOfBirth;
    private String email;
}