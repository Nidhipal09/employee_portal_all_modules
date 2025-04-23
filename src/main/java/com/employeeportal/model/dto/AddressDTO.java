package com.employeeportal.model.dto;

import lombok.Data;

import java.util.List;
@Data
public class AddressDTO {
    private Long primaryId;
    private PermanentAddressDTO permanentAddressDTO;
    private CurrentAddressDTO currentAddressDTO;
    private List<AddressDetailsDTO> addressDetailsDTOList;


}
