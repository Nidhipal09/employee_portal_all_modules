package com.employeeportal.serviceImpl;

import com.employeeportal.exception.BadRequestException;
import com.employeeportal.exception.NotFoundException;
import com.employeeportal.model.*;
import com.employeeportal.model.dto.OtherDetailsDTO;
import com.employeeportal.repository.OtherDetailsRepository;
import com.employeeportal.repository.PassportDetailsRepository;
import com.employeeportal.repository.PrimaryDetailsRepository;
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
    @Autowired
    private PrimaryDetailsRepository primaryDetailsRepository;

    @Override
    public OtherDetails saveOtherDetails(OtherDetailsDTO otherDetails) {
        Optional<PrimaryDetails> primary = primaryDetailsRepository.findById(otherDetails.getPrimaryId());
        if (!primary.isPresent()) {
            throw new NotFoundException("user not found");
        }
        Optional<OtherDetails> otherDetailsOptional = otherDetailsRepo.findByPrimaryDetails(primary.get());
        if (otherDetailsOptional.isPresent()) {
            throw new BadRequestException("other details alreday exists");
        }
        OtherDetails other = new OtherDetails();
        other.setDeclarationAccepted(otherDetails.getDeclarationAccepted());
        other.setIllness(otherDetails.getIllness());
        other.setSelfIntroduction(otherDetails.getSelfIntroduction());
        other.setPrimaryDetails(primary.get());
        OtherDetails addOtherDetails = otherDetailsRepo.save(other);
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
        return otherDetails;
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
        OtherDetails otherDetailsDelete = otherDetailsRepo.findById(otherId).orElse(null);
        if (otherDetailsDelete != null) {
            otherDetailsRepo.delete(otherDetailsDelete);
            return otherDetailsDelete;
        } else {
            throw new RuntimeException("otherDetails not found with id: " + otherId);
        }
    }

    @Override
    public OtherDetails updateOtherDetails(OtherDetailsDTO otherDetails) {
        OtherDetails existingOtherDetails = otherDetailsRepo.findById(otherDetails.getOtherId()).orElse(null);

        if (existingOtherDetails != null) {
            // Update only valid values
            if (isValidValue(otherDetails.getIllness())) {
                existingOtherDetails.setIllness(otherDetails.getIllness());
            }

            if (isValidValue(otherDetails.getSelfIntroduction())) {
                existingOtherDetails.setSelfIntroduction(otherDetails.getSelfIntroduction());
            }
            existingOtherDetails.setDeclarationAccepted(otherDetails.getDeclarationAccepted() !=null?otherDetails.getDeclarationAccepted():existingOtherDetails.getDeclarationAccepted());
            // Save and return updated details
            return otherDetailsRepo.save(existingOtherDetails);
        } else {
            // Return null or throw an exception if not found
            return null;
        }
    }
}






