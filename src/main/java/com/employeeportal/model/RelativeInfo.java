package com.employeeportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

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

    public RelativeInfo() {
    }

    public RelativeInfo(String employeeId, Long relativeId, String name, String relationship, String department, String location, String remarks, EmployeeRelative employeeRelative) {
        this.employeeId = employeeId;
        this.relativeId = relativeId;
        this.name = name;
        this.relationship = relationship;
        this.department = department;
        this.location = location;
        this.remarks = remarks;
        this.employeeRelative = employeeRelative;
    }

    public Long getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(Long relativeId) {
        this.relativeId = relativeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public EmployeeRelative getEmployeeRelative() {
        return employeeRelative;
    }

    public void setEmployeeRelative(EmployeeRelative employeeRelative) {
        this.employeeRelative = employeeRelative;
    }
}
