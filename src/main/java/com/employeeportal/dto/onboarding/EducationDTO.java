package com.employeeportal.dto.onboarding;

import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class EducationDTO {
    private String degreeName;
    private String subject;

    @Pattern(regexp = "^(19|20)\\d{2}$", message = "Passing year must be a four-digit number")
    private String passingYear;
    private String rollNumber;

    @Pattern(regexp = "^\\d{1,2}(\\.\\d)?(\\s?.*)?$", message = "Grade or percentage must be valid)")
    private String gradeOrPercentage;
    private String passingCertificate;
    private String degreeCertificate;
}