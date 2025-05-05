package com.employeeportal.controller.onboarding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeportal.model.onboarding.OnboardingDetails;
import com.employeeportal.service.onboarding.OnboardingService;;

@RestController
@RequestMapping("/onboarding")
public class OnboardingController {

    @Autowired
    private OnboardingService onboardingService;

    @PostMapping
    public void fillOnboardingDetails(@RequestBody OnboardingDetails onboardingDetails) {
        onboardingService.fillOnboardingDetails(onboardingDetails);
    }

    @GetMapping
    public OnboardingDetails getOnboardingDetails(@RequestBody int employeeId) {
        return onboardingService.getOnboardingDetails(employeeId);
    }

}
