package com.employeeportal.service.onboarding;

import com.employeeportal.model.onboarding.OnboardingDetails;

public interface OnboardingService {
    OnboardingDetails fillOnboardingDetails(OnboardingDetails onboardingDetails);

    OnboardingDetails getOnboardingDetails(String email);
}
