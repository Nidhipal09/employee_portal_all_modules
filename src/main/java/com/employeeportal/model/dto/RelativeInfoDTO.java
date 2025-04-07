package com.employeeportal.model.dto;

public class RelativeInfoDTO {
    private Long relativeId;
    private String name;
    private String employeeId;
    private String relationship;
    private String department;
    private String location;
    private String remarks;

    public RelativeInfoDTO() {
    }

    public RelativeInfoDTO(Long relativeId, String name, String employeeId, String relationship, String department, String location, String remarks) {
        this.relativeId = relativeId;
        this.name = name;
        this.employeeId = employeeId;
        this.relationship = relationship;
        this.department = department;
        this.location = location;
        this.remarks = remarks;
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
}
