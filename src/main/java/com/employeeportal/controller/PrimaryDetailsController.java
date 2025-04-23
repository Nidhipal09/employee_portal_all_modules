package com.employeeportal.controller;

import java.util.List;
import java.util.Map;

import com.employeeportal.exception.NotFoundException;
import com.employeeportal.exception.ResourceNotFoundException;
import com.employeeportal.model.OtpResponse;
import com.employeeportal.model.ResponseDTO;
import com.employeeportal.model.dto.PrimaryDetailsDTO;
import com.employeeportal.model.dto.ValidateOtpTokenResponse;
import com.employeeportal.util.JwtUtil;
import com.employeeportal.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.dto.SendOtpDto;
import com.employeeportal.service.PrimaryDetailsService;

@RestController
@RequestMapping("/primaryDetails")
@CrossOrigin(origins = "*")
public class PrimaryDetailsController {
    private final ResponseUtil responseUtil;
    private final JwtUtil jwtUtil;
    private final PrimaryDetailsService primaryDetailsService;
    @Autowired
    public PrimaryDetailsController(ResponseUtil responseUtil, JwtUtil jwtUtil, PrimaryDetailsService primaryDetailsService) {
        this.responseUtil = responseUtil;
        this.jwtUtil = jwtUtil;
        this.primaryDetailsService = primaryDetailsService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<PrimaryDetails> savePrimaryDetails(@RequestBody PrimaryDetailsDTO primaryDetails) {
        PrimaryDetails addprimaryDetails = primaryDetailsService.savePrimaryDetails(primaryDetails);
        return new ResponseEntity<>(addprimaryDetails, HttpStatus.CREATED);

    }
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    public ResponseEntity<PrimaryDetails> createPrimaryDetails(@RequestBody PrimaryDetailsDTO primaryDetails) {
        PrimaryDetails addprimaryDetails = primaryDetailsService.createPrimaryDetails(primaryDetails);
        return new ResponseEntity<>(addprimaryDetails, HttpStatus.CREATED);

    }

@GetMapping("/allPrimaryDetails")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<List<PrimaryDetails>> getAllPrimaryDetails() {
        List<PrimaryDetails> allPrimaryDetails = primaryDetailsService.getAllPrimaryDetails();
        return new ResponseEntity<>(allPrimaryDetails, HttpStatus.OK);
    }

    @GetMapping("/primary/{primaryId}")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<PrimaryDetails> getPrimaryDetailsById(@PathVariable("primaryId") Long  primaryId) {
        PrimaryDetails primaryDetailsById = primaryDetailsService.getPrimaryDetailsById( primaryId);

        if (primaryDetailsById == null) {
            throw new ResourceNotFoundException("User not found with id " +  primaryId);
        }
        return new ResponseEntity<>(primaryDetailsById, HttpStatus.OK);
    }

    @PutMapping("/update/{primaryId}")
    public ResponseEntity<PrimaryDetails> updatePrimaryDetailsById(@PathVariable("primaryId") Long primaryId, @RequestBody PrimaryDetails primaryDetails) {
        PrimaryDetails updatePrimaryDetails = primaryDetailsService. updatePrimaryDetailsById(primaryId, primaryDetails);
        // return ResponseEntity.ok(updateUser);
        return new ResponseEntity<>(updatePrimaryDetails, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{primaryId}")
    public ResponseEntity<String> deletePrimaryDetailsById(@PathVariable("primaryId") Long primaryId) {
        PrimaryDetails isDeleted = primaryDetailsService.deletePrimaryDetailsById(primaryId);
        return new ResponseEntity<>("data deleted", HttpStatus.OK);
    }

//    @PostMapping("/sendOtp")
//    public ResponseEntity<?> sendEmailOtp(@RequestBody SendOtpDto email) {
//          primaryDetailsService.sendEmailOtp(email);
//        return new ResponseEntity<>( HttpStatus.OK);
//
//    }
@PostMapping("/sendOtp")
public ResponseEntity<?> sendEmailOtp(@RequestBody SendOtpDto email) {
    String otp = primaryDetailsService.sendEmailOtp(email);
    return ResponseEntity.ok(new OtpResponse(otp));
}

    @PostMapping("/validate")
  //  @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> validateOtp(@RequestParam String email, @RequestParam String otp) {
        try {
            boolean isValid = primaryDetailsService.validateOtp(email, otp);
            ValidateOtpTokenResponse validateResponse = new ValidateOtpTokenResponse();

            if (isValid) {
                validateResponse.setToken(jwtUtil.generateToken(email));
            }else {
                throw new NotFoundException();
            }
            ResponseDTO response = responseUtil.prepareResponseDto(validateResponse,
                    "Otp is Valid",
                    HttpStatus.OK.value(), true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            ResponseDTO response = responseUtil.prepareResponseDto(null,
                    "Invalid or expired OTP",
                    HttpStatus.BAD_REQUEST.value(), false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/resendOtp")
    public ResponseEntity<?> resendOtp(@RequestBody SendOtpDto email) {
        String otp = primaryDetailsService.resendOtp(email.getEmail());
        return ResponseEntity.ok(new OtpResponse(otp));
    }
    @PostMapping("/resend-activation-link")
    public ResponseEntity<String> resendActivationLink(@RequestParam String email) {

        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required.");
        }
        try {
            String activationLink = primaryDetailsService.resendActivationLink(email);
            return ResponseEntity.ok("Activation link resent successfully: " + activationLink);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to resend activation link.");
        }
    }

}

	
	
	
	
	


