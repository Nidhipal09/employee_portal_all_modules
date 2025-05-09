package com.employeeportal.dto.onboarding;

import lombok.Data;
import java.sql.Date;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
public class PassportDetailsDTO {

    @Pattern(regexp = "^[A-PR-WYa-pr-wy][0-9]{7}$", message = "Passport number must start with a letter followed by 7 digits")
    private String passportNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfIssue;
    private String placeOfIssue;
    private String countryOfIssue;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date validUpto;
    private String nationality;
    private String passportUrl;

    @JsonIgnore
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