package com.employeeportal.model.dto;

import lombok.Data;

@Data
public class ProfessionalReferencesDTO {
    private Long professionalId;
    private String name;
    private String designation;
    private String email;
    private String contactNumber;


}
