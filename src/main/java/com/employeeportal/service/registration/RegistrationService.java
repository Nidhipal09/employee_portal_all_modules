package com.employeeportal.service.registration;

import java.util.List;

import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.dto.PreviewDetailsDTO;
import com.employeeportal.model.dto.PrimaryDetailsDTO;
import com.employeeportal.model.dto.PrimaryPreviewResponse;
import com.employeeportal.model.dto.SendOtpDto;
import com.employeeportal.model.registration.Employee;

public interface RegistrationService {

	// PrimaryDetails savePrimaryDetails(PrimaryDetailsDTO primaryDetails);

	// PrimaryDetails createPrimaryDetails(PrimaryDetailsDTO primaryDetails);

	// List<PrimaryDetails> getAllPrimaryDetails();

	// PrimaryDetails getPrimaryDetailsById(Long primaryId);

	// PrimaryDetails updatePrimaryDetailsById(Long primaryId, PrimaryDetails primaryDetails);

	// PrimaryDetails deletePrimaryDetailsById(Long primaryId);

	String sendEmailOtp(SendOtpDto email);

	boolean validateOtp(String email, String otp);

	String resendOtp(String email);

	Employee findByEmail(String email); 

	void createUser(Employee user);

	void sendPasswordResetEmail(String email) throws Exception;

	String generateResetToken(String email);

	void resetPassword(String token, String newPassword) throws Exception; // New method for resetting password

	String resendActivationLink(String email);

	void tokenLogout(String jwtToken);

	// PrimaryPreviewResponse getPrimaryDetails(Long primaryId);

	// String sendPreviewDetailsToHR(PreviewDetailsDTO previewDetailsDTO);
}