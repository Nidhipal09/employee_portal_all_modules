package com.employeeportal.serviceImpl;

import com.employeeportal.exception.BadRequestException;
import com.employeeportal.exception.NotFoundException;
import com.employeeportal.model.PersonalDetails;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.dto.PersonalDetailsDTO;
import com.employeeportal.repository.EmployeeDetailsRepository;
import com.employeeportal.repository.PersonalDetailsRepository;
import com.employeeportal.repository.PrimaryDetailsRepository;
import com.employeeportal.service.EmployeeDetailsService;
import com.employeeportal.service.PersonalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalDetailsServiceImpl   implements PersonalDetailsService {
    @Autowired
    private PersonalDetailsRepository personalDetailsRepo;
    @Autowired
    private PrimaryDetailsRepository primaryDetailsRepository;
    @Override
    public PersonalDetails savePersonalDetails(PersonalDetailsDTO personalDetail) {
        Optional<PrimaryDetails> primaryDetailsOptional = primaryDetailsRepository.findById(personalDetail.getPrimaryId());
        Optional<PersonalDetails> personalDetailsOptional = personalDetailsRepo.findByPrimaryDetails(primaryDetailsOptional.get());
        if(personalDetailsOptional.isPresent()){
            throw new BadRequestException("personal details alreday exists");
        }
        PersonalDetails personalDetails = new PersonalDetails();
        personalDetails.setFirstName(personalDetail.getFirstName());
        personalDetails.setMiddleName(personalDetail.getMiddleName());
        personalDetails.setSurname(personalDetail.getSurname());
        personalDetails.setImageUrl(personalDetail.getImageUrl());
        personalDetails.setDob(personalDetail.getDob());  // Ensure correct date format
        personalDetails.setGender(personalDetail.getGender());
        personalDetails.setMotherName(personalDetail.getMotherName());
        personalDetails.setFatherName(personalDetail.getFatherName());
        personalDetails.setEmail(personalDetail.getEmail());
        personalDetails.setMobile(personalDetail.getMobile());
        personalDetails.setAadharNumber(personalDetail.getAadharNumber());
        personalDetails.setAadharUrl(personalDetail.getAadharUrl());
        personalDetails.setPanNumber(personalDetail.getPanNumber());
        personalDetails.setPanUrl(personalDetail.getPanUrl());
        personalDetails.setPassportNumber(personalDetail.getPassportNumber());
        personalDetails.setPassportUrl(personalDetail.getPassportUrl());
if(primaryDetailsOptional.isPresent()){
    personalDetails.setPrimaryDetails(primaryDetailsOptional.get());
}else{
    throw new NotFoundException("user not present");
}

        return personalDetailsRepo.save(personalDetails);

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

    @Override
    public PersonalDetails updatePersonalDetails(PersonalDetailsDTO personalDetails) {
        PersonalDetails existingPersonalDetails = personalDetailsRepo.findById(personalDetails.getPersonalId()).orElse(null);

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


}
