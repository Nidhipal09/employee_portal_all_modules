package com.employeeportal.serviceImpl;

import com.employeeportal.model.OtherDetails;
import com.employeeportal.model.PassportDetails;
import com.employeeportal.model.PermanentAddress;
import com.employeeportal.repository.OtherDetailsRepository;
import com.employeeportal.repository.PassportDetailsRepository;
import com.employeeportal.service.OtherDetailsService;
import com.employeeportal.service.PassportDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class OtherDetailsServiceImpl   implements OtherDetailsService {
    @Autowired
    private OtherDetailsRepository otherDetailsRepo;
    @Override
    public OtherDetails saveOtherDetails(OtherDetails otherDetails) {
        OtherDetails addOtherDetails =otherDetailsRepo.save(otherDetails);
        return addOtherDetails;
    }

    @Override
    public List<OtherDetails> getAllOtherDetails() {
        List<OtherDetails> otherDetail = otherDetailsRepo.findAll();
        return otherDetail;
    }

    @Override
    public OtherDetails getOtherDetailsById(Long otherId) {
        OtherDetails otherDetails = otherDetailsRepo.findById(otherId).orElse(null);
        return  otherDetails;
    }

    @Override
    public OtherDetails updateOtherDetailsById(Long otherId, OtherDetails otherDetails) {
        // Check if the otherDetails object is null
        if (otherDetails == null) {
            throw new IllegalArgumentException("OtherDetails cannot be null");
        }

        // Fetch the existing OtherDetails by ID
        Optional<OtherDetails> existingOtherDetailsOptional = otherDetailsRepo.findById(otherId);

        // Check if the OtherDetails object with the given ID exists
        if (!existingOtherDetailsOptional.isPresent()) {
            throw new EntityNotFoundException("OtherDetails not found for ID: " + otherId);
        }

        OtherDetails existingOtherDetails = existingOtherDetailsOptional.get();

        // Update fields only if the value is not null or empty
        if (isValidValue(otherDetails.getIllness())) {
            existingOtherDetails.setIllness(otherDetails.getIllness());
        }

        if (isValidValue(otherDetails.getSelfIntroduction())) {
            existingOtherDetails.setSelfIntroduction(otherDetails.getSelfIntroduction());
        }

        // Save and return the updated OtherDetails
        otherDetailsRepo.save(existingOtherDetails);

        return existingOtherDetails;
    }

    // Helper method to check if a string value is not null or empty
    private boolean isValidValue(String value) {
        return value != null && !value.trim().isEmpty();
    }

    @Override
    public OtherDetails deleteOtherDetailsById(Long otherId) {
        OtherDetails  otherDetailsDelete =  otherDetailsRepo.findById(otherId).orElse(null);
        if (otherDetailsDelete != null) {
            otherDetailsRepo.delete(otherDetailsDelete);
            return otherDetailsDelete;
        } else {
            throw new RuntimeException("otherDetails not found with id: " + otherId);
        }
    }
    }

