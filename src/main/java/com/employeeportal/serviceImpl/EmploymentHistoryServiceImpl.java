package com.employeeportal.serviceImpl;

import com.employeeportal.model.EmploymentHistory;
import com.employeeportal.model.PermanentAddress;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.repository.EmploymentHistoryRepository;
import com.employeeportal.repository.PrimaryDetailsRepository;
import com.employeeportal.service.EmploymentHistoryService;
import com.employeeportal.service.PrimaryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class EmploymentHistoryServiceImpl   implements EmploymentHistoryService {
    @Autowired
    private EmploymentHistoryRepository employmentHistoryRepo;
    @Override
    public EmploymentHistory saveEmploymentHistory(EmploymentHistory employmentHistory) {
        EmploymentHistory addEmploymentHistory = employmentHistoryRepo.save(employmentHistory);
        return addEmploymentHistory;
    }

    @Override
    public List<EmploymentHistory> getAllEmploymentHistory() {
        List<EmploymentHistory> employmentHistory = employmentHistoryRepo.findAll();
        return employmentHistory;
    }

    @Override
    public EmploymentHistory getEmploymentHistoryById(Long employmentId) {
        EmploymentHistory permanentAddres = employmentHistoryRepo.findById(employmentId).orElse(null);
        return permanentAddres;
    }

    @Override
    public EmploymentHistory updateEmploymentHistoryById(Long employmentId, EmploymentHistory employmentHistory) {
        // Fetch the existing employment history by ID
        EmploymentHistory existingEmploymentHistory = employmentHistoryRepo.findById(employmentId).orElse(null);

        // Check if the existing employment history was found
        if (existingEmploymentHistory != null) {
            // Update the existing employment history with the values from the incoming employmentHistory object
            existingEmploymentHistory.setPreviousEmployerName(isValidValue(employmentHistory.getPreviousEmployerName())
                    ? employmentHistory.getPreviousEmployerName()
                    : existingEmploymentHistory.getPreviousEmployerName());

            existingEmploymentHistory.setEmployerAddress(isValidValue(employmentHistory.getEmployerAddress())
                    ? employmentHistory.getEmployerAddress()
                    : existingEmploymentHistory.getEmployerAddress());

            existingEmploymentHistory.setTelephoneNumber(isValidValue(employmentHistory.getTelephoneNumber())
                    ? employmentHistory.getTelephoneNumber()
                    : existingEmploymentHistory.getTelephoneNumber());

            existingEmploymentHistory.setDesignation(isValidValue(employmentHistory.getDesignation())
                    ? employmentHistory.getDesignation()
                    : existingEmploymentHistory.getDesignation());

            existingEmploymentHistory.setManagerName(isValidValue(employmentHistory.getManagerName())
                    ? employmentHistory.getManagerName()
                    : existingEmploymentHistory.getManagerName());

            existingEmploymentHistory.setManagerEmail(isValidValue(employmentHistory.getManagerEmail())
                    ? employmentHistory.getManagerEmail()
                    : existingEmploymentHistory.getManagerEmail());

            existingEmploymentHistory.setReasonsForLeaving(isValidValue(employmentHistory.getReasonsForLeaving())
                    ? employmentHistory.getReasonsForLeaving()
                    : existingEmploymentHistory.getReasonsForLeaving());

            existingEmploymentHistory.setEmploymentStartDate(employmentHistory.getEmploymentStartDate() != null
                    ? employmentHistory.getEmploymentStartDate()
                    : existingEmploymentHistory.getEmploymentStartDate());

            existingEmploymentHistory.setEmploymentEndDate(employmentHistory.getEmploymentEndDate() != null
                    ? employmentHistory.getEmploymentEndDate()
                    : existingEmploymentHistory.getEmploymentEndDate());

            existingEmploymentHistory.setEmploymentType(isValidValue(employmentHistory.getEmploymentType())
                    ? employmentHistory.getEmploymentType()
                    : existingEmploymentHistory.getEmploymentType());

            existingEmploymentHistory.setExperienceCertificateUrl(isValidValue(employmentHistory.getExperienceCertificateUrl())
                    ? employmentHistory.getExperienceCertificateUrl()
                    : existingEmploymentHistory.getExperienceCertificateUrl());
existingEmploymentHistory.setDepartment(isValidValue(employmentHistory.getDepartment())?employmentHistory.getDepartment():existingEmploymentHistory.getDepartment());
            existingEmploymentHistory.setRelievingLetterUrl(isValidValue(employmentHistory.getRelievingLetterUrl())
                    ? employmentHistory.getRelievingLetterUrl()
                    : existingEmploymentHistory.getRelievingLetterUrl());

            existingEmploymentHistory.setLastMonthSalarySlipUrl(isValidValue(employmentHistory.getLastMonthSalarySlipUrl())
                    ? employmentHistory.getLastMonthSalarySlipUrl()
                    : existingEmploymentHistory.getLastMonthSalarySlipUrl());

            existingEmploymentHistory.setAppointmentLetterUrl(isValidValue(employmentHistory.getAppointmentLetterUrl())
                    ? employmentHistory.getAppointmentLetterUrl()
                    : existingEmploymentHistory.getAppointmentLetterUrl());

            // Save the updated employment history
            employmentHistoryRepo.save(existingEmploymentHistory);

            // Return the updated employment history
            return existingEmploymentHistory;
        } else {
            // If no matching employment history were found, return null or throw an exception
            return null; // Or throw a custom exception here if necessary, e.g., throw new NotFoundException("Employment history not found.");
        }
    }

    // Helper method to check if a string value is not null or blank
    private boolean isValidValue(String value) {
        return value != null && !value.trim().isEmpty();
    }


    @Override
    public EmploymentHistory deleteEmploymentHistoryById(Long employmentId) {
        EmploymentHistory employmentHistoryDelete = employmentHistoryRepo.findById(employmentId).orElse(null);
        if (employmentHistoryDelete != null) {
            employmentHistoryRepo.delete(employmentHistoryDelete);
            return employmentHistoryDelete;
        } else {
            throw new RuntimeException("EmploymentHistory not found with id: " + employmentId);
        }

    }
}
