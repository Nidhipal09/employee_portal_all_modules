package com.employeeportal.dto.onboarding;

import lombok.Data;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class VisaDetailsDTO {
    private String workPermitDetails;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date workPermitValidTill;
    private String passportCopy;
    private String passportCopyUrl;
    private String status;
    private String country;

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