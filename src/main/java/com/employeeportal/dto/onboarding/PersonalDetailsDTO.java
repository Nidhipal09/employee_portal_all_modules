package com.employeeportal.dto.onboarding;

import lombok.Data;

@Data
public class PersonalDetailsDTO {
    private String imageUrl;
    private String gender;
    private String motherName;
    private String fatherName;
    private String secondaryMobile;
    private String fullName;
    private String personalEmail;
}