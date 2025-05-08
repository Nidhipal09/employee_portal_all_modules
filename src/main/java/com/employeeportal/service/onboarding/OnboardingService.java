package com.employeeportal.service.onboarding;

import com.employeeportal.dto.onboarding.OnboardingResponseDTO;
import com.employeeportal.model.onboarding.OnboardingDetails;

public interface OnboardingService {
    OnboardingResponseDTO fillOnboardingDetails(OnboardingDetails onboardingDetails, String email, String pageIdentifier);

    OnboardingResponseDTO getOnboardingDetails(String email, String pageIdentifier);
}
