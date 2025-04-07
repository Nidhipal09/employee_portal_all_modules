package com.employeeportal.service;

import com.employeeportal.model.PassportDetails;
import com.employeeportal.model.PrimaryDetails;

import java.util.List;

public interface PassportDetailsService {

    PassportDetails savePassportDetails(PassportDetails passportDetails);
    List<PassportDetails> getAllPassportDetails();
    PassportDetails getPassportDetailsById(Long passportId);
    PassportDetails updatePassportDetailsById(Long passportId, PassportDetails passportDetails);
    PassportDetails deletePassportDetailsById(Long passportId);
}
