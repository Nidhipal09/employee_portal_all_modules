package com.employeeportal.service;

import com.employeeportal.model.AddressDetails;
import com.employeeportal.model.AddressResponseDTO;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.dto.AddressDTO;

import java.util.List;

public interface AddressDetailsService {
    AddressResponseDTO saveAddressDetails(AddressDTO addressDetails);
    List<AddressDetails> getAllAddressDetails();
    AddressDetails getAddressDetailsById(Long addressId);
    AddressDetails updateAddressDetailsById(Long addressId, AddressDetails addressDetails);
    AddressDetails deleteAddressDetailsById(Long addressId);

    AddressResponseDTO updateAddressDetails(AddressDTO addressDetails);
}
