package com.employeeportal.model.dto;

import lombok.Data;

@Data
public class RelativeInfoDTO {
    private Long relativeId;
    private String name;
    private String employeeId;
    private String relationship;
    private String department;
    private String location;
    private String remarks;


}
