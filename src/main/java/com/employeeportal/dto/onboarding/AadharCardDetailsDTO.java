package com.employeeportal.dto.onboarding;

import javax.validation.constraints.Pattern;

import com.employeeportal.model.onboarding.IdentityType;

import lombok.Data;

@Data
public class AadharCardDetailsDTO {

    @Pattern(regexp = "^\\d{12}$", message = "Aadhar Identification Number must be valid")
    private String aadharIdentificationNumber;
    private String aadharIdentificationUrl;

    public boolean isNull() {
        return this == null ||
               (this.aadharIdentificationNumber == null &&
                this.aadharIdentificationUrl == null);
    }
}