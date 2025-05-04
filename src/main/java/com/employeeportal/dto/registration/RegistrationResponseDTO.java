package com.employeeportal.dto.registration;

import lombok.Data;

@Data
public class RegistrationResponseDTO {

    private int employeeId;
    private String message;

    public RegistrationResponseDTO(int employeeId, String message) {
        this.employeeId = employeeId;
        this.message = message;
    }
}
