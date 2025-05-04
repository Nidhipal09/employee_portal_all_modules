package com.employeeportal.dto.onboarding;

import lombok.Data;
import java.sql.Date;

@Data
public class AddressDTO {;
    private String addressType;
    private String address1;
    private String address2;
    private String address3;
    private String zip;
    private String state;
    private String city;
    private String country;
    private Date stayFrom;
    private Date stayTo;
    private String emergencyContactNumber;
    private String emergencyContactNameAndRelationship;
}