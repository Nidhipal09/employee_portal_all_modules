package com.employeeportal.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeResponseDTO {
    private Long primaryId;
    private String Designation;
    private LocalDate joiningDate;
}
