package com.employeeportal.service;

import com.employeeportal.model.PermanentAddress;
import com.employeeportal.model.PersonalDetails;

import java.util.List;

public interface PermanentAddressService {

    PermanentAddress savePermanentAddress(PermanentAddress permanentAddress);
    List<PermanentAddress> getAllPermanentAddress();
    PermanentAddress getPermanentAddressById(Long permanentId);
    PermanentAddress updatePermanentAddressById(Long permanentId, PermanentAddress permanentAddress);
    PermanentAddress deletePermanentAddressById(Long permanentId);
}
