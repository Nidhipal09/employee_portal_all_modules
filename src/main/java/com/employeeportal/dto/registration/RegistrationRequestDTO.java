package com.employeeportal.dto.registration;

import java.sql.Date;
import java.time.LocalDate;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class RegistrationRequestDTO {

    private String fullName;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Email must be a valid email address")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Mobile number must be exactly 10 digits and contain only numbers")
    private String mobileNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    private String status;
}