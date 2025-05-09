package com.employeeportal.model.onboarding;

import javax.persistence.*;

import com.employeeportal.model.registration.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "employee_organization_details")
public class EmployeeOrganizationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeOrganizationId;

    @Column(name = "employee_code", length = 50)
    private String employeeCode;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    @Column(name = "designation", length = 100)
    private String designation;

    @Column(name = "reporting_manager", length = 100)
    private String reportingManager;

    @Column(name = "reporting_hr", length = 100)
    private String reportingHr;

    @Column(name = "joining_date")
    private LocalDate joiningDate;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "employee_id")
    private Employee employee;

}