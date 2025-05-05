package com.employeeportal.service.onboarding;

import com.employeeportal.model.onboarding.OnboardingDetails;

public interface OnboardingService {
    void fillOnboardingDetails(OnboardingDetails onboardingDetails);

    OnboardingDetails getOnboardingDetails(int employeeId);
}
