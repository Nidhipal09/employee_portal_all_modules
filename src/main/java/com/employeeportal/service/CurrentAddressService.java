package com.employeeportal.service;

import com.employeeportal.model.CurrentAddress;
import com.employeeportal.model.PermanentAddress;

import java.util.List;

public interface CurrentAddressService {
    CurrentAddress saveCurrentAddress(CurrentAddress currentAddress);
    List<CurrentAddress> getAllCurrentAddress();
    CurrentAddress getCurrentAddressById(Long currentId);
    CurrentAddress updateCurrentAddressById(Long currentId, CurrentAddress currentAddress);
    CurrentAddress deleteCurrentAddressById(Long currentId);


}
