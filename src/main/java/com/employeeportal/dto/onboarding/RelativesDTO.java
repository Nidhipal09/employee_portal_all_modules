package com.employeeportal.dto.onboarding;

import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class RelativesDTO {

    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only letters and spaces")
    private String name;
    private String relativeEmployeeId;
    private String relationship;
    private String department;
    private String designation;
}