package com.employeeportal.service.onboarding;

import java.util.List;

import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.dto.PreviewDetailsDTO;
import com.employeeportal.model.dto.PrimaryDetailsDTO;
import com.employeeportal.model.dto.PrimaryPreviewResponse;
import com.employeeportal.model.dto.SendOtpDto;

public interface EmployeeService {

	void sendPasswordResetEmail(String email) throws Exception;

	String generateResetToken(String email);

	void resetPassword(String token, String newPassword) throws Exception;

	void tokenLogout(String jwtToken);
}