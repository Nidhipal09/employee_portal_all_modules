package com.employeeportal.model.dto;

public class SearchDTO {
    private Long employeeId;
    private String name;
    private String projects;

    public SearchDTO(Long employeeId, String name, String projects) {
        this.employeeId = employeeId;
        this.name = name;
        this.projects = projects;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }
}
