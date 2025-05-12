package com.employeeportal.dto.onboarding;

import lombok.Data;
import java.sql.Date;
import java.time.LocalDate;

import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
public class PassportDetailsDTO {

    @Pattern(regexp = "^[A-PR-WYa-pr-wy][0-9]{7}$", message = "Passport number must start with a letter followed by 7 digits")
    private String passportNumber;

    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([0-9]{4})$", message = "Date must be in the format dd/MM/yyyy")
    private String dateOfIssue;
    private String placeOfIssue;
    private String countryOfIssue;

    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([0-9]{4})$", message = "Date must be in the format dd/MM/yyyy")
    private String validUpto;
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