package com.employeeportal.dto.onboarding;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class PersonalDetailsDTO {
    private String imageUrl;
    private String gender;
    private String motherName;
    private String fatherName;

    @Pattern(regexp = "\\d{10}", message = "Secondary mobile must be exactly 10 digits")
    private String secondaryMobile;
    private String fullName;

    @JsonIgnore
    public boolean isNull() {
        return this == null ||
               (this.imageUrl == null &&
                this.gender == null &&
                this.motherName == null &&
                this.fatherName == null &&
                this.secondaryMobile == null &&
                this.fullName == null);
    }
}