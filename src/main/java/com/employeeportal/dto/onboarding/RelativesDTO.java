package com.employeeportal.dto.onboarding;

import lombok.Data;

@Data
public class RelativesDTO {
    private String name;
    private String employeeRelativeId;
    private String relationship;
    private String department;
    private String location;
    private String remarks;
}