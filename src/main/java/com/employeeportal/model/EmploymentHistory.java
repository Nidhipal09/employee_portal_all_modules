package com.employeeportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="employment_history")
public class EmploymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employmentId;
    private String previousEmployerName;
    private String employerAddress;
    private String telephoneNumber;
    private String employeeCode;
    private String designation;
    private String department;
    private String managerName;
    private String managerEmail;
    private String managerContactNo;
    private String reasonsForLeaving;
    private String employmentStartDate;
    private String  employmentEndDate;
    private String employmentType;
    private String experienceCertificateUrl;
    private String relievingLetterUrl;
    private String lastMonthSalarySlipUrl;
    private String appointmentLetterUrl;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "primary_id", nullable = false)
    private PrimaryDetails primaryDetails;

    public EmploymentHistory() {
    }

    public EmploymentHistory(Long employmentId, String previousEmployerName, String employerAddress, String telephoneNumber, String employeeCode, String designation, String department, String managerName, String managerEmail, String managerContactNo, String reasonsForLeaving, String employmentStartDate, String employmentEndDate, String employmentType, String experienceCertificateUrl, String relievingLetterUrl, String lastMonthSalarySlipUrl, String appointmentLetterUrl, PrimaryDetails primaryDetails) {
        this.employmentId = employmentId;
        this.previousEmployerName = previousEmployerName;
        this.employerAddress = employerAddress;
        this.telephoneNumber = telephoneNumber;
        this.employeeCode = employeeCode;
        this.designation = designation;
        this.department = department;
        this.managerName = managerName;
        this.managerEmail = managerEmail;
        this.managerContactNo = managerContactNo;
        this.reasonsForLeaving = reasonsForLeaving;
        this.employmentStartDate = employmentStartDate;
        this.employmentEndDate = employmentEndDate;
        this.employmentType = employmentType;
        this.experienceCertificateUrl = experienceCertificateUrl;
        this.relievingLetterUrl = relievingLetterUrl;
        this.lastMonthSalarySlipUrl = lastMonthSalarySlipUrl;
        this.appointmentLetterUrl = appointmentLetterUrl;
        this.primaryDetails = primaryDetails;
    }

    public Long getEmploymentId() {
        return employmentId;
    }

    public void setEmploymentId(Long employmentId) {
        this.employmentId = employmentId;
    }

    public String getPreviousEmployerName() {
        return previousEmployerName;
    }

    public void setPreviousEmployerName(String previousEmployerName) {
        this.previousEmployerName = previousEmployerName;
    }

    public String getEmployerAddress() {
        return employerAddress;
    }

    public void setEmployerAddress(String employerAddress) {
        this.employerAddress = employerAddress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getManagerContactNo() {
        return managerContactNo;
    }

    public void setManagerContactNo(String managerContactNo) {
        this.managerContactNo = managerContactNo;
    }

    public String getReasonsForLeaving() {
        return reasonsForLeaving;
    }

    public void setReasonsForLeaving(String reasonsForLeaving) {
        this.reasonsForLeaving = reasonsForLeaving;
    }

    public String getEmploymentStartDate() {
        return employmentStartDate;
    }

    public void setEmploymentStartDate(String employmentStartDate) {
        this.employmentStartDate = employmentStartDate;
    }

    public String getEmploymentEndDate() {
        return employmentEndDate;
    }

    public void setEmploymentEndDate(String employmentEndDate) {
        this.employmentEndDate = employmentEndDate;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getExperienceCertificateUrl() {
        return experienceCertificateUrl;
    }

    public void setExperienceCertificateUrl(String experienceCertificateUrl) {
        this.experienceCertificateUrl = experienceCertificateUrl;
    }

    public String getRelievingLetterUrl() {
        return relievingLetterUrl;
    }

    public void setRelievingLetterUrl(String relievingLetterUrl) {
        this.relievingLetterUrl = relievingLetterUrl;
    }

    public String getLastMonthSalarySlipUrl() {
        return lastMonthSalarySlipUrl;
    }

    public void setLastMonthSalarySlipUrl(String lastMonthSalarySlipUrl) {
        this.lastMonthSalarySlipUrl = lastMonthSalarySlipUrl;
    }

    public String getAppointmentLetterUrl() {
        return appointmentLetterUrl;
    }

    public void setAppointmentLetterUrl(String appointmentLetterUrl) {
        this.appointmentLetterUrl = appointmentLetterUrl;
    }

    public PrimaryDetails getPrimaryDetails() {
        return primaryDetails;
    }

    public void setPrimaryDetails(PrimaryDetails primaryDetails) {
        this.primaryDetails = primaryDetails;
    }
}
