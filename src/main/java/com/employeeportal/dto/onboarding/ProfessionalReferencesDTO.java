package com.employeeportal.dto.onboarding;

import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class ProfessionalReferencesDTO {

    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only letters and spaces")
    private String name;
    private String designation;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Email must be a valid email address")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Contact number must be exactly 10 digits")
    private String contactNumber;
}