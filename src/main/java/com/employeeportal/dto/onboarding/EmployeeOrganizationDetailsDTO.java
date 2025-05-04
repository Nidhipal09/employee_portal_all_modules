package com.employeeportal.dto.onboarding;

import lombok.Data;
import java.sql.Date;

@Data
public class EmployeeOrganizationDetailsDTO {
    private String employeeCode;
    private String designation;
    private String reportingManager;
    private String reportingHr;
    private Date joiningDate;
}