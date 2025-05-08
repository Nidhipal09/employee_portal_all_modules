package com.employeeportal.controller.onboarding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeeportal.model.onboarding.OnboardingDetails;
import com.employeeportal.service.onboarding.OnboardingService;;

@RestController
@RequestMapping("/onboarding")
public class OnboardingController {

    @Autowired
    private OnboardingService onboardingService;

    @PostMapping
    public ResponseEntity<OnboardingDetails> fillOnboardingDetails(@RequestBody OnboardingDetails onboardingDetails, @RequestParam String email, @RequestParam String pageIdentifier) {
        return new ResponseEntity<>(onboardingService.fillOnboardingDetails(onboardingDetails, email, pageIdentifier), HttpStatus.OK);
    }

    // @PostMapping
    // public ResponseEntity<?> fillOnboardingDetails(@RequestBody OnboardingDetails onboardingDetails, @RequestParam String email, @RequestParam String pageIdentifier) {
    //     System.out.println("kdjfkjfkdjfkjkjfkjjjjjjjjjj"+ onboardingDetails.toString());
    //     return new ResponseEntity<>(HttpStatus.OK);
    // }

    // @GetMapping
    // public ResponseEntity<OnboardingDetails> getOnboardingDetails(@RequestParam String email, @RequestParam String pageIdentifier) {
    //     OnboardingDetails onboardingDetails = onboardingService.getOnboardingDetails(email, pageIdentifier);
    //     return new ResponseEntity<>(onboardingDetails, HttpStatus.ACCEPTED);
    // }

    @GetMapping
    public ResponseEntity<?> getOnboardingDetails(@RequestParam String email, @RequestParam String pageIdentifier) {
        System.out.println("kdjfkjfkdjfkjkjfkjjjjjjjjjj"+ email + pageIdentifier);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
