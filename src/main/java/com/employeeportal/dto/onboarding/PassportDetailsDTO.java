package com.employeeportal.dto.onboarding;

import lombok.Data;
import java.sql.Date;

@Data
public class PassportDetailsDTO {
    private String passportNumber;
    private Date dateOfIssue;
    private String placeOfIssue;
    private String countryOfIssue;
    private Date validUpto;
    private String nationality;
    private String passportUrl;

    public boolean isNull() {
        return (this == null) ||
                (this.getPassportNumber() == null &&
                        this.getDateOfIssue() == null &&
                        this.getPlaceOfIssue() == null &&
                        this.getCountryOfIssue() == null &&
                        this.getValidUpto() == null &&
                        this.getNationality() == null &&
                        this.getPassportUrl() == null);
    }

}