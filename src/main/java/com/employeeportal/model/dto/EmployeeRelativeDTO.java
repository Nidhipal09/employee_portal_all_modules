package com.employeeportal.model.dto;

import java.util.List;

public class EmployeeRelativeDTO {
    private Long employeeRelativeId;
    private Boolean hasRelative;
    private List<RelativeInfoDTO> relativeInfoDTOS;

    public EmployeeRelativeDTO() {
    }

    public EmployeeRelativeDTO(Long employeeRelativeId, Boolean hasRelative, List<RelativeInfoDTO> relativeInfoDTOS) {
        this.employeeRelativeId = employeeRelativeId;
        this.hasRelative = hasRelative;
        this.relativeInfoDTOS = relativeInfoDTOS;
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

    public List<RelativeInfoDTO> getRelativeInfoDTOS() {
        return relativeInfoDTOS;
    }

    public void setRelativeInfoDTOS(List<RelativeInfoDTO> relativeInfoDTOS) {
        this.relativeInfoDTOS = relativeInfoDTOS;
    }
}
