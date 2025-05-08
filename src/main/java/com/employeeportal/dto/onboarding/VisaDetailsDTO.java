package com.employeeportal.dto.onboarding;

import lombok.Data;
import java.sql.Date;

@Data
public class VisaDetailsDTO {
    private String workPermitDetails;
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