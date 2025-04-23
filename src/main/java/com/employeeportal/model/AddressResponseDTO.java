package com.employeeportal.model;

import lombok.Data;

import java.util.List;

@Data
public class AddressResponseDTO {
    private PermanentAddress permanentAddress;
    private CurrentAddress currentAddress;
    private List<AddressDetails> addressDetailsList;
}
