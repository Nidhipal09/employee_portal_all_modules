package com.employeeportal.service.onboarding;

import com.employeeportal.model.onboarding.OnboardingDetails;

public interface OnboardingService {
    OnboardingDetails fillOnboardingDetails(OnboardingDetails onboardingDetails, String email, String pageIdentifier);

    OnboardingDetails getOnboardingDetails(String email, String pageIdentifier);
}
