package com.employeeportal.dto.onboarding;

import lombok.Data;
import java.sql.Date;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
public class VisaDetailsDTO {
    private String workPermitDetails;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate workPermitValidTill;
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