package com.employeeportal.dto.onboarding;

import lombok.Data;
import java.sql.Date;
import java.time.LocalDate;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class EmploymentHistoryDTO {
    private String previousEmployerName;
    private String address;

    @Pattern(regexp = "\\d{10}", message = "Telephone number must be exactly 10 digits")
    private String telephoneNumber;
    private String employeeCode;
    private String designation;
    private String department;
    private String managerName;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Email must be a valid email address")
    private String managerEmail;

    @Pattern(regexp = "\\d{10}", message = "Contact number must be exactly 10 digits")
    private String managerContactNo;
    private String reasonsForLeaving;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate employmentPeriodFrom;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate employmentPeriodTo;
    
    private String positionType;
    private String experienceCertificateUrl;
    private String relievingLetterUrl;
    private String lastMonthSalarySlipUrl;
    private String appointmentLetterUrl;
}