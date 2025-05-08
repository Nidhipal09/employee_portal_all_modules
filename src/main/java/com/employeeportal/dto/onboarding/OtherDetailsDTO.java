package com.employeeportal.dto.onboarding;

import lombok.Data;

@Data
public class OtherDetailsDTO {
    private String illnessDeclaration;
    private String hobbiesDeclaration;

    public boolean isNull() {
        return this == null ||
               (this.illnessDeclaration == null &&
                this.hobbiesDeclaration == null);
    }
}