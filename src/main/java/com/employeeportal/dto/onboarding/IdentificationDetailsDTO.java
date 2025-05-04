package com.employeeportal.dto.onboarding;

import com.employeeportal.model.onboarding.IdentityType;

import lombok.Data;

@Data
public class IdentificationDetailsDTO {
    private IdentityType identityType;
    private String identificationNumber;
    private String identificationUrl;
}