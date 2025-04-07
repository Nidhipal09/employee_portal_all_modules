package com.employeeportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee_relatives")
public class EmployeeRelative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeRelativeId;
    private Boolean hasRelative;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "primary_id", nullable = false)
    private PrimaryDetails primaryDetails;
    @OneToMany(mappedBy = "employeeRelative", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<RelativeInfo> relativeInfoList;

    public EmployeeRelative() {
    }

    public EmployeeRelative(Long employeeRelativeId, Boolean hasRelative, PrimaryDetails primaryDetails, List<RelativeInfo> relativeInfoList) {
        this.employeeRelativeId = employeeRelativeId;
        this.hasRelative = hasRelative;
        this.primaryDetails = primaryDetails;
        this.relativeInfoList = relativeInfoList;
    }

    public Long getEmployeeRelativeId() {
        return employeeRelativeId;
    }

    public void setEmployeeRelativeId(Long employeeRelativeId) {
        this.employeeRelativeId = employeeRelativeId;
    }

    public Boolean getHasRelative() {
        return hasRelative;
    }

    public void setHasRelative(Boolean hasRelative) {
        this.hasRelative = hasRelative;
    }

    public PrimaryDetails getPrimaryDetails() {
        return primaryDetails;
    }

    public void setPrimaryDetails(PrimaryDetails primaryDetails) {
        this.primaryDetails = primaryDetails;
    }

    public List<RelativeInfo> getRelativeInfoList() {
        return relativeInfoList;
    }

    public void setRelativeInfoList(List<RelativeInfo> relativeInfoList) {
        this.relativeInfoList = relativeInfoList;
    }
}
