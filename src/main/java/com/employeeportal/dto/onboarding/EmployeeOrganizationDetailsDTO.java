package com.employeeportal.dto.onboarding;

import lombok.Data;
import java.sql.Date;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class EmployeeOrganizationDetailsDTO {
    private String employeeCode;
    private String designation;
    private String reportingManager;
    private String reportingHr;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate joiningDate;
}