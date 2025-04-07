package com.employeeportal.serviceImpl;

import com.employeeportal.model.PersonalDetails;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.repository.EmployeeDetailsRepository;
import com.employeeportal.repository.PersonalDetailsRepository;
import com.employeeportal.service.EmployeeDetailsService;
import com.employeeportal.service.PersonalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonalDetailsServiceImpl   implements PersonalDetailsService {
    @Autowired
    private PersonalDetailsRepository personalDetailsRepo;
    @Override
    public PersonalDetails savePersonalDetails(PersonalDetails personalDetails) {
        PersonalDetails addPersonalDetails = personalDetailsRepo.save(personalDetails);
        return addPersonalDetails;

    }

    @Override
    public List<PersonalDetails> getAllPersonalDetails() {
        List<PersonalDetails> personalDetails =  personalDetailsRepo.findAll();
        return personalDetails;

    }

    @Override
    public PersonalDetails getPersonalDetailsById(Long personalId) {
        PersonalDetails personalDetail = personalDetailsRepo.findById(personalId).orElse(null);
        return personalDetail;
    }

//    @Override
//    public PersonalDetails updatePersonalDetailsById(Long personalId, PersonalDetails personalDetails) {
//        PersonalDetails existingPersonalDetails=personalDetailsRepo.findById(personalId).get();
//
//        existingPersonalDetails.setFirstName(PersonalDetails.getFirstName());
//        existingPersonalDetails.setMiddleName(PersonalDetails.getMiddleName());
//        existingPersonalDetails.setSurname(PersonalDetails.getSurname());
//        existingPersonalDetails.setImageUrl(PersonalDetails.getImageUrl());
//        existingPersonalDetails.setDob(PersonalDetails.getDob());
//        existingPersonalDetails.setGender(PersonalDetails.getGender());
//        existingPersonalDetails.setMotherName(PersonalDetails.getMotherName());
//        existingPersonalDetails.setFatherName(PersonalDetails.getFatherName());
//        existingPersonalDetails.setEmail(PersonalDetails.getEmail());
//        existingPersonalDetails.setMobile(PersonalDetails.getMobile());
//        existingPersonalDetails.setMobile(PersonalDetails.getMobile());
//        existingPersonalDetails.setAadharNumber(PersonalDetails.getAadharNumber());
//        existingPersonalDetails.setAadharUrl(PersonalDetails.getAadharUrl());
//        existingPersonalDetails.setPanNumber(PersonalDetails.getPanNumber());
//        existingPersonalDetails.setPanUrl(PersonalDetails.getPanUrl());
//        existingPersonalDetails.setPassportNumber(PersonalDetails.getPassportNumber());
//        existingPersonalDetails.setPassportUrl(PersonalDetails.getPassportUrl());
//        personalDetailsRepo.save(existingPersonalDetails);
//        return existingPersonalDetails;
//    }
//@Override
//public PersonalDetails updatePersonalDetailsById(Long personalId, PersonalDetails personalDetails) {
//    // Fetch the existing personal details by ID
//    PersonalDetails existingPersonalDetails = personalDetailsRepo.findById(personalId).orElse(null);
//
//    // Check if the existing personal details were found
//    if (existingPersonalDetails != null) {
//        // Update the existing personal details with the values from the incoming personalDetails object
//        existingPersonalDetails.setFirstName(personalDetails.getFirstName());
//        existingPersonalDetails.setMiddleName(personalDetails.getMiddleName());
//        existingPersonalDetails.setSurname(personalDetails.getSurname());
//        existingPersonalDetails.setImageUrl(personalDetails.getImageUrl());
//        existingPersonalDetails.setDob(personalDetails.getDob());
//        existingPersonalDetails.setGender(personalDetails.getGender());
//        existingPersonalDetails.setMotherName(personalDetails.getMotherName());
//        existingPersonalDetails.setFatherName(personalDetails.getFatherName());
//        existingPersonalDetails.setEmail(personalDetails.getEmail());
//        existingPersonalDetails.setMobile(personalDetails.getMobile());
//        existingPersonalDetails.setAadharNumber(personalDetails.getAadharNumber());
//        existingPersonalDetails.setAadharUrl(personalDetails.getAadharUrl());
//        existingPersonalDetails.setPanNumber(personalDetails.getPanNumber());
//        existingPersonalDetails.setPanUrl(personalDetails.getPanUrl());
//        existingPersonalDetails.setPassportNumber(personalDetails.getPassportNumber());
//        existingPersonalDetails.setPassportUrl(personalDetails.getPassportUrl());
//
//        // Save the updated personal details
//        personalDetailsRepo.save(existingPersonalDetails);
//
//        // Return the updated personal details
//        return existingPersonalDetails;
//    } else {
//        // If no matching personal details were found, return null or throw an exception
//        return null;
//    }
//}
//@Override
//public PersonalDetails updatePersonalDetailsById(Long personalId, PersonalDetails personalDetails) {
//    // Fetch the existing personal details by ID
//    PersonalDetails existingPersonalDetails = personalDetailsRepo.findById(personalId).orElse(null);
//
//    // Check if the existing personal details were found
//    if (existingPersonalDetails != null) {
//        // Update the existing personal details with the values from the incoming personalDetails object
//        existingPersonalDetails.setFirstName(personalDetails.getFirstName() == null ? existingPersonalDetails.getFirstName() : personalDetails.getFirstName());
//        existingPersonalDetails.setMiddleName(personalDetails.getMiddleName() == null ? existingPersonalDetails.getMiddleName() : personalDetails.getMiddleName());
//        existingPersonalDetails.setSurname(personalDetails.getSurname() == null ? existingPersonalDetails.getSurname() : personalDetails.getSurname());
//        existingPersonalDetails.setImageUrl(personalDetails.getImageUrl() == null ? existingPersonalDetails.getImageUrl() : personalDetails.getImageUrl());
//        existingPersonalDetails.setDob(personalDetails.getDob() == null ? existingPersonalDetails.getDob() : personalDetails.getDob());
//        existingPersonalDetails.setGender(personalDetails.getGender() == null ? existingPersonalDetails.getGender() : personalDetails.getGender());
//        existingPersonalDetails.setMotherName(personalDetails.getMotherName() == null ? existingPersonalDetails.getMotherName() : personalDetails.getMotherName());
//        existingPersonalDetails.setFatherName(personalDetails.getFatherName() == null ? existingPersonalDetails.getFatherName() : personalDetails.getFatherName());
//        existingPersonalDetails.setEmail(personalDetails.getEmail() == null ? existingPersonalDetails.getEmail() : personalDetails.getEmail());
//        existingPersonalDetails.setMobile(personalDetails.getMobile() == null ? existingPersonalDetails.getMobile() : personalDetails.getMobile());
//        existingPersonalDetails.setAadharNumber(personalDetails.getAadharNumber() == null ? existingPersonalDetails.getAadharNumber() : personalDetails.getAadharNumber());
//        existingPersonalDetails.setAadharUrl(personalDetails.getAadharUrl() == null ? existingPersonalDetails.getAadharUrl() : personalDetails.getAadharUrl());
//        existingPersonalDetails.setPanNumber(personalDetails.getPanNumber() == null ? existingPersonalDetails.getPanNumber() : personalDetails.getPanNumber());
//        existingPersonalDetails.setPanUrl(personalDetails.getPanUrl() == null ? existingPersonalDetails.getPanUrl() : personalDetails.getPanUrl());
//        existingPersonalDetails.setPassportNumber(personalDetails.getPassportNumber() == null ? existingPersonalDetails.getPassportNumber() : personalDetails.getPassportNumber());
//        existingPersonalDetails.setPassportUrl(personalDetails.getPassportUrl() == null ? existingPersonalDetails.getPassportUrl() : personalDetails.getPassportUrl());
//
//        // Save the updated personal details
//        personalDetailsRepo.save(existingPersonalDetails);
//
//        // Return the updated personal details
//        return existingPersonalDetails;
//    } else {
//        // If no matching personal details were found, return null or throw an exception
//        return null; // Or throw a custom exception here if necessary, e.g., throw new NotFoundException("Personal details not found.");
//    }
//}
@Override
public PersonalDetails updatePersonalDetailsById(Long personalId, PersonalDetails personalDetails) {
    // Fetch the existing personal details by ID
    PersonalDetails existingPersonalDetails = personalDetailsRepo.findById(personalId).orElse(null);

    // Check if the existing personal details were found
    if (existingPersonalDetails != null) {
        // Update the existing personal details with the values from the incoming personalDetails object
        existingPersonalDetails.setFirstName(isValidValue(personalDetails.getFirstName()) ? personalDetails.getFirstName() : existingPersonalDetails.getFirstName());
        existingPersonalDetails.setMiddleName(isValidValue(personalDetails.getMiddleName()) ? personalDetails.getMiddleName() : existingPersonalDetails.getMiddleName());
        existingPersonalDetails.setSurname(isValidValue(personalDetails.getSurname()) ? personalDetails.getSurname() : existingPersonalDetails.getSurname());
        existingPersonalDetails.setImageUrl(isValidValue(personalDetails.getImageUrl()) ? personalDetails.getImageUrl() : existingPersonalDetails.getImageUrl());
        existingPersonalDetails.setDob(personalDetails.getDob() == null ? existingPersonalDetails.getDob() : personalDetails.getDob());
        existingPersonalDetails.setGender(personalDetails.getGender() == null ? existingPersonalDetails.getGender() : personalDetails.getGender());
        existingPersonalDetails.setMotherName(isValidValue(personalDetails.getMotherName()) ? personalDetails.getMotherName() : existingPersonalDetails.getMotherName());
        existingPersonalDetails.setFatherName(isValidValue(personalDetails.getFatherName()) ? personalDetails.getFatherName() : existingPersonalDetails.getFatherName());
        existingPersonalDetails.setEmail(isValidValue(personalDetails.getEmail()) ? personalDetails.getEmail() : existingPersonalDetails.getEmail());
        existingPersonalDetails.setMobile(isValidValue(personalDetails.getMobile()) ? personalDetails.getMobile() : existingPersonalDetails.getMobile());
        existingPersonalDetails.setAadharNumber(isValidValue(personalDetails.getAadharNumber()) ? personalDetails.getAadharNumber() : existingPersonalDetails.getAadharNumber());
        existingPersonalDetails.setAadharUrl(isValidValue(personalDetails.getAadharUrl()) ? personalDetails.getAadharUrl() : existingPersonalDetails.getAadharUrl());
        existingPersonalDetails.setPanNumber(isValidValue(personalDetails.getPanNumber()) ? personalDetails.getPanNumber() : existingPersonalDetails.getPanNumber());
        existingPersonalDetails.setPanUrl(isValidValue(personalDetails.getPanUrl()) ? personalDetails.getPanUrl() : existingPersonalDetails.getPanUrl());
        existingPersonalDetails.setPassportNumber(isValidValue(personalDetails.getPassportNumber()) ? personalDetails.getPassportNumber() : existingPersonalDetails.getPassportNumber());
        existingPersonalDetails.setPassportUrl(isValidValue(personalDetails.getPassportUrl()) ? personalDetails.getPassportUrl() : existingPersonalDetails.getPassportUrl());

        // Save the updated personal details
        personalDetailsRepo.save(existingPersonalDetails);

        // Return the updated personal details
        return existingPersonalDetails;
    } else {
        // If no matching personal details were found, return null or throw an exception
        return null; // Or throw a custom exception here if necessary, e.g., throw new NotFoundException("Personal details not found.");
    }
}

    // Helper method to check if a string value is not null or blank
    private boolean isValidValue(String value) {
        return value != null && !value.trim().isEmpty();
    }



    @Override
    public PersonalDetails deletePersonalDetailsById(Long personalId) {
        PersonalDetails personalDetailsDelete = personalDetailsRepo.findById(personalId).orElse(null);
        if (personalDetailsDelete != null) {
            personalDetailsRepo.delete(personalDetailsDelete);
            return personalDetailsDelete;
        } else {
            throw new RuntimeException("PersonalDetails not found with id: " + personalId);
        }
    }
}
