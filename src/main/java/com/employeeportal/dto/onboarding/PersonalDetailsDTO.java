package com.employeeportal.dto.onboarding;

import lombok.Data;

@Data
public class PersonalDetailsDTO {
    private String imageUrl;
    private String gender;
    private String motherName;
    private String fatherName;
    private String secondaryMobile;
    private String fullName;

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