package com.employeeportal.service;

import com.employeeportal.model.PersonalDetails;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.dto.PersonalDetailsDTO;

import java.util.List;

public interface PersonalDetailsService {

    PersonalDetails savePersonalDetails(PersonalDetailsDTO personalDetails);
    List<PersonalDetails> getAllPersonalDetails();
    PersonalDetails getPersonalDetailsById(Long personalId);
    PersonalDetails updatePersonalDetailsById(Long personalId, PersonalDetails personalDetails);
    PersonalDetails deletePersonalDetailsById(Long personalId);

    PersonalDetails updatePersonalDetails(PersonalDetailsDTO personalDetails);
}
