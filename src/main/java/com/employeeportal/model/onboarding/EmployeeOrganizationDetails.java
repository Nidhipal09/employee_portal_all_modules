package com.employeeportal.model.onboarding;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "employee_organization_details")
public class EmployeeOrganizationDetails {

    @Column(name = "employee_code", nullable = false, length = 50)
    private String employeeCode;

    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;

    @Column(name = "designation", nullable = false, length = 100)
    private String designation;

    @Column(name = "reporting_manager", length = 100)
    private String reportingManager;

    @Column(name = "reporting_hr", length = 100)
    private String reportingHr;

    @Column(name = "joining_date", nullable = false)
    private Date joiningDate;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;

}