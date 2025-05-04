package com.employeeportal.dto.onboarding;

import lombok.Data;
import java.sql.Date;

@Data
public class EmploymentHistoryDTO {
    private String previousEmployerName;
    private String address;
    private String telephoneNumber;
    private String employeeCode;
    private String designation;
    private String department;
    private String managerName;
    private String managerEmail;
    private String managerContactNo;
    private String reasonsForLeaving;
    private Date employmentPeriodFrom;
    private Date employmentPeriodTo;
    private String positionType;
    private String experienceCertificateUrl;
    private String relievingLetterUrl;
    private String lastMonthSalarySlipUrl;
    private String appointmentLetterUrl;
}