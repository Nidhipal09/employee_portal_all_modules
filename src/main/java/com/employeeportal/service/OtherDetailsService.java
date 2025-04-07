package com.employeeportal.service;

import com.employeeportal.model.OtherDetails;
import com.employeeportal.model.PermanentAddress;

import java.util.List;

public interface OtherDetailsService {

    OtherDetails saveOtherDetails(OtherDetails otherDetails);
    List<OtherDetails> getAllOtherDetails();
    OtherDetails getOtherDetailsById(Long otherId);
    OtherDetails updateOtherDetailsById(Long otherId, OtherDetails otherDetails);
    OtherDetails deleteOtherDetailsById(Long otherId);
}
