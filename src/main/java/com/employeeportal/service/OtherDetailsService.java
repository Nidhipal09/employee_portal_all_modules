package com.employeeportal.service;

import com.employeeportal.model.OtherDetails;
import com.employeeportal.model.PermanentAddress;
import com.employeeportal.model.dto.OtherDetailsDTO;

import java.util.List;

public interface OtherDetailsService {

    OtherDetails saveOtherDetails(OtherDetailsDTO otherDetails);
    List<OtherDetails> getAllOtherDetails();
    OtherDetails getOtherDetailsById(Long otherId);
    OtherDetails updateOtherDetailsById(Long otherId, OtherDetails otherDetails);
    OtherDetails deleteOtherDetailsById(Long otherId);

    OtherDetails updateOtherDetails(OtherDetailsDTO otherDetails);


}
