package com.employeeportal.serviceImpl;

import com.employeeportal.model.PermanentAddress;
import com.employeeportal.model.PersonalDetails;
import com.employeeportal.model.Preview;
import com.employeeportal.repository.PersonalDetailsRepository;
import com.employeeportal.repository.PreviewRepository;
import com.employeeportal.service.PreviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PreviewServiceImpl  implements PreviewService {
    @Autowired
    private PreviewRepository previewRepo;
    @Override
    public Preview savePreview(Preview preview) {
        Preview addPreview = previewRepo.save(preview);
        return addPreview;

    }

    @Override
    public List<Preview> getAllPreview() {
        List<Preview> preview =  previewRepo.findAll();
        return preview;
    }

    @Override
    public Preview getPreviewById(Long previewId) {
        Preview previews = previewRepo.findById(previewId).orElse(null);
        return previews;
    }

    @Override
    public Preview updatePreviewById(Long previewId, Preview preview) {
        // Check if the preview object is null
        if (preview == null) {
            throw new IllegalArgumentException("Preview cannot be null");
        }

        // Fetch the existing Preview by ID
        Optional<Preview> existingPreviewOptional = previewRepo.findById(previewId);

        // Check if the Preview object with the given ID exists
        if (!existingPreviewOptional.isPresent()) {
            throw new EntityNotFoundException("Preview not found for ID: " + previewId);
        }

        Preview existingPreview = existingPreviewOptional.get();

        // Update fields only if the value is not null or empty

        // Update EmployeeSignatureUrl if the value is not null or empty
        if (isValidValue(preview.getEmployeeSignatureUrl())) {
            existingPreview.setEmployeeSignatureUrl(preview.getEmployeeSignatureUrl());
        }

        // Update SignatureDate if the value is not null
        if (preview.getSignatureDate() != null) {
            existingPreview.setSignatureDate(preview.getSignatureDate());
        }

        // Update Place if the value is not null or empty
        if (isValidValue(preview.getPlace())) {
            existingPreview.setPlace(preview.getPlace());
        }

        // Save and return the updated Preview
        previewRepo.save(existingPreview);

        return existingPreview;
    }

    // Helper method to check if a string value is not null or empty
    private boolean isValidValue(String value) {
        return value != null && !value.trim().isEmpty();
    }


    @Override
    public Preview deletePreviewById(Long previewId) {
        Preview previewDelete = previewRepo.findById(previewId).orElse(null);
        if (previewDelete != null) {
            previewRepo.delete(previewDelete);
            return previewDelete;
        } else {
            throw new RuntimeException("Preview not found with id: " + previewId);
        }
    }
    }

