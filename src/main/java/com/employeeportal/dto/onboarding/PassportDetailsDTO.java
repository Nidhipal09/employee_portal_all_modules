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
}