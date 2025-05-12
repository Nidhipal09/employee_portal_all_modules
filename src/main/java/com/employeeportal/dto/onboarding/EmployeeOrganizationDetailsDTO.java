package com.employeeportal.dto.onboarding;

import lombok.Data;
import java.sql.Date;
import java.time.LocalDate;

import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class EmployeeOrganizationDetailsDTO {
    private String employeeCode;
    private String designation;
    private String reportingManager;
    private String reportingHr;

    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([0-9]{4})$", message = "Date must be in the format dd/MM/yyyy")
    private String joiningDate;
}