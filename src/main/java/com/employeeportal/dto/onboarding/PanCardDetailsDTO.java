package com.employeeportal.dto.onboarding;

import javax.validation.constraints.Pattern;

import com.employeeportal.model.onboarding.IdentityType;

import lombok.Data;

@Data
public class PanCardDetailsDTO {

    @Pattern(regexp = "^[A-Z]{5}\\d{4}[A-Z]$", message = "PAN Identification Number must be valid")
    private String panIdentificationNumber;
    private String panIdentificationUrl;

    public boolean isNull() {
        return this == null ||
               (this.panIdentificationNumber == null &&
                this.panIdentificationUrl == null);
    }
}