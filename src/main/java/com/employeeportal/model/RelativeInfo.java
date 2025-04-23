package com.employeeportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name="Relative_Info")
public class RelativeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long relativeId;
    private String name;
    private String employeeId;
    private String relationship;
    private String department;
    private String location;
    private String remarks;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "employee_relative_id", nullable = false)
    private EmployeeRelative employeeRelative;


}
