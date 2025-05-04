package com.employeeportal.dto.onboarding;

import lombok.Data;

@Data
public class EducationDTO {
    private String degreeName;
    private String subject;
    private String passingYear;
    private String rollNumber;
    private String gradeOrPercentage;
    private String passingCertificate;
    private String degreeCertificate;
}