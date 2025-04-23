package com.employeeportal.model.dto;

public class ReportingManagerDTO {
    private Long reportingManagerId;
    private String name;

    public Long getReportingManagerId() {
        return reportingManagerId;
    }

    public void setReportingManagerId(Long reportingManagerId) {
        this.reportingManagerId = reportingManagerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
