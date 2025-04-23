package com.employeeportal.service;

import java.util.List;

import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.dto.PreviewDetailsDTO;
import com.employeeportal.model.dto.PrimaryDetailsDTO;
import com.employeeportal.model.dto.PrimaryPreviewResponse;
import com.employeeportal.model.dto.SendOtpDto;

public interface PrimaryDetailsService {
	
	PrimaryDetails savePrimaryDetails(PrimaryDetailsDTO primaryDetails);
	PrimaryDetails createPrimaryDetails(PrimaryDetailsDTO primaryDetails);
	List<PrimaryDetails> getAllPrimaryDetails();
	PrimaryDetails getPrimaryDetailsById(Long primaryId);
	PrimaryDetails updatePrimaryDetailsById(Long primaryId, PrimaryDetails primaryDetails);
	PrimaryDetails deletePrimaryDetailsById(Long primaryId);
	 String sendEmailOtp(SendOtpDto email);
	boolean validateOtp(String email, String otp);
	String resendOtp(String email);
	PrimaryDetails findByEmail(String email);  // Return User or null
	void createUser(PrimaryDetails user);
	void sendPasswordResetEmail(String email) throws Exception;
	 String generateResetToken(String email);
	void resetPassword(String token, String newPassword) throws Exception;  // New method for resetting password
	String resendActivationLink(String email);
	void tokenLogout(String jwtToken);

	PrimaryPreviewResponse getPrimaryDetails(Long primaryId);

    String sendPreviewDetailsToHR(PreviewDetailsDTO previewDetailsDTO);
}