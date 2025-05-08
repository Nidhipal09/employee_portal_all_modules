package com.employeeportal.dto.onboarding;

import lombok.Data;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class EmployeeOrganizationDetailsDTO {
    private String employeeCode;
    private String designation;
    private String reportingManager;
    private String reportingHr;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date joiningDate;
}