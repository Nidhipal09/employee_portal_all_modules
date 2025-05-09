package com.employeeportal.controller.onboarding;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeeportal.dto.onboarding.OnboardingResponseDTO;
import com.employeeportal.model.onboarding.ErrorResponse;
import com.employeeportal.model.onboarding.OnboardingDetails;
import com.employeeportal.service.onboarding.OnboardingService;;

@RestController
@RequestMapping("/onboarding")
public class OnboardingController {

    @Autowired
    private OnboardingService onboardingService;

    @PutMapping
    public ResponseEntity<OnboardingResponseDTO> fillOnboardingDetails(@RequestBody @Valid OnboardingDetails onboardingDetails,
            @RequestParam String email, @RequestParam String pageIdentifier) {
        OnboardingResponseDTO onboardingResponseDTO = onboardingService.fillOnboardingDetails(onboardingDetails, email,
                pageIdentifier);
        return new ResponseEntity<>(onboardingResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<OnboardingResponseDTO> getOnboardingDetails(@RequestParam String email,
            @RequestParam String pageIdentifier) {
        OnboardingResponseDTO onboardingResponseDTO = onboardingService.getOnboardingDetails(email, pageIdentifier);
        return new ResponseEntity<>(onboardingResponseDTO, HttpStatus.OK);
    }

}
