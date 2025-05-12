package com.employeeportal.dto.onboarding;

import com.employeeportal.model.onboarding.OnboardingDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PreviewResponseDTO {
    private OnboardingDetails onboardingDetails;
    private EmployeeDTO employee;
    private String status;
}
