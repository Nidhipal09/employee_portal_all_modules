package com.employeeportal.model.onboarding;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.sql.Date;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employment_history")
public class EmploymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employmentId;
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

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;
}
