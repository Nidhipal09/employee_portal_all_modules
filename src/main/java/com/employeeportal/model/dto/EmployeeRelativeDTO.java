package com.employeeportal.model.dto;

import lombok.Data;

import java.util.List;
@Data
public class EmployeeRelativeDTO {
    private Long employeeRelativeId;
    private Boolean hasRelative;
    private List<RelativeInfoDTO> relativeInfoDTOS;


}
