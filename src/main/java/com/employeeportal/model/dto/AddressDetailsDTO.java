package com.employeeportal.model.dto;

import lombok.Data;

@Data
public class AddressDetailsDTO {
    private Long addressId;
    private String stayFrom;
    private String stayTo;
    private String addressLine;
    private String pincode;
    private String country;
    private String contactNumberWithRelationship;


}
