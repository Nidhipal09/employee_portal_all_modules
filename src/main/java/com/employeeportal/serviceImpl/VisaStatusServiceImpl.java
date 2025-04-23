package com.employeeportal.serviceImpl;

import com.employeeportal.exception.NotFoundException;
import com.employeeportal.model.CurrentAddress;
import com.employeeportal.model.VisaStatus;
import com.employeeportal.repository.VisaStatusRepository;
import com.employeeportal.service.VisaStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class VisaStatusServiceImpl implements VisaStatusService {

    @Autowired
    private VisaStatusRepository visaStatusRepo;


    @Override
    public VisaStatus getVisaStatusById(Long visaId) {
        VisaStatus visaStatus = visaStatusRepo.findById(visaId).orElse(null);
        return visaStatus;
    }

    @Override
    public List<VisaStatus> getAllVisaStatus() {
        List<VisaStatus> visaStatuses = visaStatusRepo.findAll();
        return visaStatuses;
    }

    @Override
    public VisaStatus saveVisaStatus(VisaStatus visaStatus) {
        VisaStatus addVisaStatus = visaStatusRepo.save(visaStatus);
        return addVisaStatus;
    }


    @Override
    public VisaStatus updateVisaStatusById(Long visaId, VisaStatus visaStatus) {
        // Check if visaStatus is null
        if (visaStatus == null) {
            throw new IllegalArgumentException("VisaStatus cannot be null");
        }

        // Fetch existing VisaStatus by ID
        Optional<VisaStatus> existingOptional = visaStatusRepo.findById(visaId);

        // Check if the existing VisaStatus is present
        if (!existingOptional.isPresent()) {
            throw new EntityNotFoundException("VisaStatus not found for ID: " + visaId);
        }

        VisaStatus existingVisaStatus = existingOptional.get();

        // Update fields using the provided visaStatus object, but only if the value is not null or false
        existingVisaStatus.setCitizen(visaStatus.getCitizen() != existingVisaStatus.getCitizen() ? visaStatus.getCitizen() : existingVisaStatus.getCitizen());
        existingVisaStatus.setExpatOnGreenCard(visaStatus.getExpatOnGreenCard() != existingVisaStatus.getExpatOnGreenCard() ? visaStatus.getExpatOnGreenCard() : existingVisaStatus.getExpatOnGreenCard());
        existingVisaStatus.setExpatOnWorkPermit(visaStatus.getExpatOnWorkPermit() != existingVisaStatus.getExpatOnWorkPermit() ? visaStatus.getExpatOnWorkPermit() : existingVisaStatus.getExpatOnWorkPermit());
        existingVisaStatus.setExpatOnPermanentResidencyPermit(visaStatus.getExpatOnPermanentResidencyPermit() != existingVisaStatus.getExpatOnPermanentResidencyPermit() ? visaStatus.getExpatOnPermanentResidencyPermit() : existingVisaStatus.getExpatOnPermanentResidencyPermit());
        existingVisaStatus.setAnyOtherStatus(visaStatus.getAnyOtherStatus() != existingVisaStatus.getAnyOtherStatus() ? visaStatus.getAnyOtherStatus() : existingVisaStatus.getAnyOtherStatus());

        // Save the updated entity
        visaStatusRepo.save(existingVisaStatus);

        // Return the updated entity
        return existingVisaStatus;
    }
    private boolean isValidValue(String value) {
        return value != null && !value.trim().isEmpty();
    }

    @Override
    public VisaStatus deleteVisaStatusById(Long visaId) {
        VisaStatus visaStatusDelete = visaStatusRepo.findById(visaId).orElse(null);
        if (visaStatusDelete != null) {
            visaStatusRepo.delete(visaStatusDelete);
            return visaStatusDelete;
        } else {
            throw new RuntimeException("currentAddress not found with id: " + visaId);
        }
    }
}



