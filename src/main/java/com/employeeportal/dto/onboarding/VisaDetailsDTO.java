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
}