package com.employeeportal.service;

import com.employeeportal.model.AddressDetails;
import com.employeeportal.model.PrimaryDetails;

import java.util.List;

public interface AddressDetailsService {
    AddressDetails saveAddressDetails(AddressDetails addressDetails);
    List<AddressDetails> getAllAddressDetails();
    AddressDetails getAddressDetailsById(Long addressId);
    AddressDetails updateAddressDetailsById(Long addressId, AddressDetails addressDetails);
    AddressDetails deleteAddressDetailsById(Long addressId);
}
