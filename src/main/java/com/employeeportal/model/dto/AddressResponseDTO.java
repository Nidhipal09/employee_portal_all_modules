package com.employeeportal.model.dto;

import lombok.Data;

@Data
public class AddressResponseDTO {
    private Long AddressId;
    private String houseName;         // Shiksha Sadan
    private String houseNumber;       // House No-PY/12
    private String street;            // D.v.c road
    private String area;              // Gardanibagh
    private String city;              // Gaya
    private String state;
    private String postalCode;
}
