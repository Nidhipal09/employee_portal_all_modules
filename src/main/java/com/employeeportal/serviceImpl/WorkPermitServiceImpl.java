package com.employeeportal.serviceImpl;

import com.employeeportal.model.VisaStatus;
import com.employeeportal.model.WorkPermit;
import com.employeeportal.repository.WorkPermitRepository;
import com.employeeportal.service.WorkPermitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class WorkPermitServiceImpl implements WorkPermitService {

    @Autowired
    private WorkPermitRepository workPermitRepository;

    @Override
    public WorkPermit createWorkPermit(WorkPermit workPermit) {
        return workPermitRepository.save(workPermit);
    }

    @Override
    public WorkPermit getWorkPermitById(Long workId) {
        return workPermitRepository.findById(workId).orElse(null);
    }

    @Override
    public List<WorkPermit> getAllWorkPermit() {
        return workPermitRepository.findAll();
    }

    @Override
    public WorkPermit updateWorkPermit(Long workId, WorkPermit workPermit) {
        // Check if workPermit is null before proceeding
        if (workPermit == null) {
            throw new IllegalArgumentException("WorkPermit cannot be null");
        }

        // Fetch the existing WorkPermit by ID
        return workPermitRepository.findById(workId).map(existingWorkPermit -> {
            // Update fields if the new value is not null or empty

            // Update legalRightToWork if the value is not null
            if (workPermit.getLegalRightToWork() != null) {
                existingWorkPermit.setLegalRightToWork(workPermit.getLegalRightToWork());
            }

            // Update workPermitDetails if the value is not null or empty
            if (isValidValue(workPermit.getWorkPermitDetails())) {
                existingWorkPermit.setWorkPermitDetails(workPermit.getWorkPermitDetails());
            }

            // Update workPermitValidTill if the value is not null
            if (workPermit.getWorkPermitValidTill() != null) {
                existingWorkPermit.setWorkPermitValidTill(workPermit.getWorkPermitValidTill());
            }

            // Update passportCopy if the value is not null or empty
            if (isValidValue(workPermit.getPassportCopy())) {
                existingWorkPermit.setPassportCopy(workPermit.getPassportCopy());
            }

            // Update passportCopyPath if the value is not null or empty
            if (isValidValue(workPermit.getPassportCopyPath())) {
                existingWorkPermit.setPassportCopyPath(workPermit.getPassportCopyPath());
            }

            // Save and return the updated WorkPermit
            return workPermitRepository.save(existingWorkPermit);
        }).orElseThrow(() -> new EntityNotFoundException("WorkPermit not found for ID: " + workId)); // If no WorkPermit found, throw an exception
    }
    private boolean isValidValue(String value) {
        return value != null && !value.trim().isEmpty();
    }

    @Override
    public WorkPermit deleteWorkPermitById(Long workId) {
        WorkPermit workPermitDelete = workPermitRepository.findById(workId).orElse(null);
        if (workPermitDelete != null) {
            workPermitRepository.delete(workPermitDelete);
            return workPermitDelete;
        } else {
            throw new RuntimeException("currentAddress not found with id: " + workId);
        }
    }

    }






