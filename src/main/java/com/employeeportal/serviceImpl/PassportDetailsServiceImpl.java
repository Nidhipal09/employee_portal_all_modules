package com.employeeportal.serviceImpl;

import com.employeeportal.model.AddressDetails;
import com.employeeportal.model.PassportDetails;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.ProfessionalReferences;
import com.employeeportal.repository.PassportDetailsRepository;
import com.employeeportal.repository.PrimaryDetailsRepository;
import com.employeeportal.service.PassportDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassportDetailsServiceImpl  implements PassportDetailsService {
    @Autowired
    private PassportDetailsRepository passportDetailsRepo;
    @Override
    public PassportDetails savePassportDetails(PassportDetails passportDetails) {
        PassportDetails addPassportDetails = passportDetailsRepo.save(passportDetails);
        return addPassportDetails;
    }

    @Override
    public List<PassportDetails> getAllPassportDetails() {
        List<PassportDetails> passportDetail = passportDetailsRepo.findAll();
        return passportDetail;
    }


    @Override
    public PassportDetails getPassportDetailsById(Long passportId) {
        PassportDetails passportDetails = passportDetailsRepo.findById(passportId).orElse(null);
        return passportDetails;
    }



    @Override
    public PassportDetails updatePassportDetailsById(Long passportId, PassportDetails passportDetails) {
        // Retrieve the existing PassportDetails from the database by ID
        PassportDetails existingPassportDetails = passportDetailsRepo.findById(passportId)
                .orElseThrow(() -> new RuntimeException("Passport details not found for ID: " + passportId));

        // Update fields only if they are valid (non-null and non-empty)
        existingPassportDetails.setPassportNumber(isValidValue(passportDetails.getPassportNumber()) ? passportDetails.getPassportNumber() : existingPassportDetails.getPassportNumber());
        existingPassportDetails.setIssueDate(passportDetails.getIssueDate() != null ? passportDetails.getIssueDate() : existingPassportDetails.getIssueDate());
        existingPassportDetails.setPlaceOfIssue(isValidValue(passportDetails.getPlaceOfIssue()) ? passportDetails.getPlaceOfIssue() : existingPassportDetails.getPlaceOfIssue());
        existingPassportDetails.setExpiryDate(passportDetails.getExpiryDate() != null ? passportDetails.getExpiryDate() : existingPassportDetails.getExpiryDate());
        existingPassportDetails.setCountryOfIssue(isValidValue(passportDetails.getCountryOfIssue()) ? passportDetails.getCountryOfIssue() : existingPassportDetails.getCountryOfIssue());
        existingPassportDetails.setNationality(isValidValue(passportDetails.getNationality()) ? passportDetails.getNationality() : existingPassportDetails.getNationality());

        // Save the updated PassportDetails
        passportDetailsRepo.save(existingPassportDetails);

        // Return the updated PassportDetails
        return existingPassportDetails;
    }

    // Helper method to check if a string value is not null or blank
    private boolean isValidValue(String value) {
        return value != null && !value.trim().isEmpty();
    }


    @Override
    public PassportDetails deletePassportDetailsById(Long passportId) {
        PassportDetails passportDetailsDelete = passportDetailsRepo.findById(passportId).orElse(null);
        if (passportDetailsDelete != null) {
            passportDetailsRepo.delete(passportDetailsDelete);
            return passportDetailsDelete;
        } else {
            throw new RuntimeException("PassportDetails not found with id: " + passportId);
        }
    }
}
