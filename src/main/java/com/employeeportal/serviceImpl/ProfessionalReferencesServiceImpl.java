package com.employeeportal.serviceImpl;

import com.employeeportal.model.AddressDetails;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.ProfessionalReferences;
import com.employeeportal.repository.AddressDetailsRepository;
import com.employeeportal.repository.ProfessionalReferencesRepository;
import com.employeeportal.service.PrimaryDetailsService;
import com.employeeportal.service.ProfessionalReferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfessionalReferencesServiceImpl  implements ProfessionalReferencesService {
    @Autowired
    private ProfessionalReferencesRepository professionalReferencesRepo;
    @Override
    public ProfessionalReferences saveProfessionalReferences(ProfessionalReferences professionalReferences) {
        ProfessionalReferences addProfessionalReferences = professionalReferencesRepo.save(professionalReferences);
        return addProfessionalReferences;
    }

    @Override
    public List<ProfessionalReferences> getAllProfessionalReferences() {
        List<ProfessionalReferences> professionalReferences = professionalReferencesRepo.findAll();
        return professionalReferences;
    }

    @Override
    public ProfessionalReferences getProfessionalReferencesById(Long professionalId) {
        ProfessionalReferences professionalReferences = professionalReferencesRepo.findById(professionalId).orElse(null);
        return professionalReferences;
    }


    @Override
    public ProfessionalReferences updateProfessionalReferencesById(Long professionalId, ProfessionalReferences professionalReferences) {
        // Fetch the existing professional references by ID
        ProfessionalReferences existingProfessionalReferences = professionalReferencesRepo.findById(professionalId).orElse(null);

        // Check if the existing professional references were found
        if (existingProfessionalReferences != null) {
            // Update the existing professional references with the values from the incoming professionalReferences object
            existingProfessionalReferences.setName(isValidValue(professionalReferences.getName())
                    ? professionalReferences.getName()
                    : existingProfessionalReferences.getName());

            existingProfessionalReferences.setDesignation(isValidValue(professionalReferences.getDesignation())
                    ? professionalReferences.getDesignation()
                    : existingProfessionalReferences.getDesignation());

            existingProfessionalReferences.setEmail(isValidValue(professionalReferences.getEmail())
                    ? professionalReferences.getEmail()
                    : existingProfessionalReferences.getEmail());

            existingProfessionalReferences.setContactNumber(isValidValue(professionalReferences.getContactNumber())
                    ? professionalReferences.getContactNumber()
                    : existingProfessionalReferences.getContactNumber());

            // Save the updated professional references
            professionalReferencesRepo.save(existingProfessionalReferences);

            // Return the updated professional references
            return existingProfessionalReferences;
        } else {
            // If no matching professional references were found, return null or throw an exception
            return null; // Or throw a custom exception here if necessary, e.g., throw new NotFoundException("Professional references not found.");
        }
    }

    // Helper method to check if a string value is not null or blank
    private boolean isValidValue(String value) {
        return value != null && !value.trim().isEmpty();
    }


    @Override
    public ProfessionalReferences deleteProfessionalReferencesById(Long professionalId) {
        ProfessionalReferences professionalReferencesDelete = professionalReferencesRepo.findById(professionalId).orElse(null);
        if (professionalReferencesDelete != null) {
            professionalReferencesRepo.delete(professionalReferencesDelete);
            return professionalReferencesDelete;
        } else {
            throw new RuntimeException("ProfessionalReferences not found with id: " + professionalId);
        }
    }
}
