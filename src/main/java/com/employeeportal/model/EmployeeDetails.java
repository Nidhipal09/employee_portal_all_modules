package com.employeeportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name="employee_details")
public class EmployeeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long employeeId;
    private LocalDate joiningDate;
    private String placeOfWork;
    private String employmentType;
//    @ManyToOne
//    @JoinColumn(name = "primary_id", nullable = false)
//    private PrimaryDetails primaryDetails;
@OneToOne
@JsonIgnore
@JoinColumn(name = "primary_id", nullable = false)
private PrimaryDetails primaryDetails;
    public EmployeeDetails() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public PrimaryDetails getPrimaryDetails() {
        return primaryDetails;
    }

    public void setPrimaryDetails(PrimaryDetails primaryDetails) {
        this.primaryDetails = primaryDetails;
    }

    public EmployeeDetails(Long employeeId, LocalDate joiningDate, String placeOfWork, String employmentType, PrimaryDetails primaryDetails) {
        this.employeeId = employeeId;
        this.joiningDate = joiningDate;
        this.placeOfWork = placeOfWork;
        this.employmentType = employmentType;
        this.primaryDetails = primaryDetails;
    }
}
