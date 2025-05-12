package com.employeeportal.dto.onboarding;

import lombok.Data;
import java.sql.Date;
import java.time.LocalDate;

import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
public class VisaDetailsDTO {
    private String workPermitDetails;

    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([0-9]{4})$", message = "Date must be in the format dd/MM/yyyy")
    private String workPermitValidTill;
    private String passportCopy;
    private String passportCopyUrl;
    private String status;
    private String country;

    @JsonIgnore
    public boolean isNull() {
        return this == null ||
               (this.workPermitDetails == null &&
                this.workPermitValidTill == null &&
                this.passportCopy == null &&
                this.passportCopyUrl == null &&
                this.status == null &&
                this.country == null);
    }
}