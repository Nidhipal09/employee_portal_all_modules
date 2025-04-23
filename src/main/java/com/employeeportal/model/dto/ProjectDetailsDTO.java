package com.employeeportal.model.dto;

import lombok.Data;

@Data
public class ProjectDetailsDTO {
    private Long projectId;
    private String client;
    private Long reportingManagerId;
    private String location;
    private Long primaryId;

}

