package com.employeeportal.service.registration;

import java.util.List;

import com.employeeportal.dto.registration.RegistrationRequestDTO;
import com.employeeportal.dto.registration.RegistrationResponseDTO;
import com.employeeportal.model.registration.Employee;

public interface RegistrationService {

	RegistrationResponseDTO registerEmployee(RegistrationRequestDTO employeeRegistrationDTO);

	String sendOtpEmail(String email);

	boolean validateOtp(String email, String otp);
	
	String resendActivationLink(String email);
	
	// Employee findByEmail(String email);

	// void createUser(Employee user);

	// PrimaryDetails savePrimaryDetails(PrimaryDetailsDTO primaryDetails);

	// List<PrimaryDetails> getAllPrimaryDetails();

	// PrimaryDetails getPrimaryDetailsById(Long primaryId);

	// PrimaryDetails updatePrimaryDetailsById(Long primaryId, PrimaryDetails
	// primaryDetails);

	// PrimaryDetails deletePrimaryDetailsById(Long primaryId);

	// PrimaryPreviewResponse getPrimaryDetails(Long primaryId);

	// String sendPreviewDetailsToHR(PreviewDetailsDTO previewDetailsDTO);
}