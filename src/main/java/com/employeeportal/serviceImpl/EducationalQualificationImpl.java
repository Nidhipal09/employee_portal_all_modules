package com.employeeportal.serviceImpl;

import com.employeeportal.model.EducationalQualification;
import com.employeeportal.model.PersonalDetails;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.repository.AddressDetailsRepository;
import com.employeeportal.repository.EducationalQualificationRepository;
import com.employeeportal.service.EducationalQualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class EducationalQualificationImpl  implements EducationalQualificationService {
    @Autowired
    private EducationalQualificationRepository educationalQualificationRepo;


    public EducationalQualification saveEducationalQualification(EducationalQualification educationalQualification) {
        EducationalQualification addEducationalQualification = educationalQualificationRepo.save(educationalQualification);
        return addEducationalQualification;
    }


    @Override
    public List<EducationalQualification> getAllEducationalQualification() {
        List<EducationalQualification> educationalQualifications = educationalQualificationRepo.findAll();
        return educationalQualifications;
    }

    @Override
    public EducationalQualification getEducationalQualificationById(Long educationalId) {
        EducationalQualification educationalQualification = educationalQualificationRepo.findById(educationalId).orElse(null);
        return educationalQualification;
    }

    @Override
    public EducationalQualification updateEducationalQualificationById(Long educationalId, EducationalQualification educationalQualification) {
        // Fetch the existing EducationalQualification by ID
        EducationalQualification existingEducationalQualification = educationalQualificationRepo.findById(educationalId)
                .orElseThrow(() -> new RuntimeException("Educational Qualification not found for ID: " + educationalId));

        // Update fields if the new value is valid (not null or blank)
        existingEducationalQualification.setDegreeName(isValidValue(educationalQualification.getDegreeName()) ? educationalQualification.getDegreeName() : existingEducationalQualification.getDegreeName());
        existingEducationalQualification.setSubject(isValidValue(educationalQualification.getSubject()) ? educationalQualification.getSubject() : existingEducationalQualification.getSubject());
        existingEducationalQualification.setPassingYear(isValidValue(educationalQualification.getPassingYear()) ? educationalQualification.getPassingYear() : existingEducationalQualification.getPassingYear());
        existingEducationalQualification.setRollNumber(isValidValue(educationalQualification.getRollNumber()) ? educationalQualification.getRollNumber() : existingEducationalQualification.getRollNumber());
        existingEducationalQualification.setGradeOrPercentage(isValidValue(educationalQualification.getGradeOrPercentage()) ? educationalQualification.getGradeOrPercentage() : existingEducationalQualification.getGradeOrPercentage());

        // Save the updated EducationalQualification
        educationalQualificationRepo.save(existingEducationalQualification);

        // Return the updated EducationalQualification
        return existingEducationalQualification;
    }

    // Helper method to check if a string value is not null or blank
    private boolean isValidValue(String value) {
        return value != null && !value.trim().isEmpty(); // Checks both null and blank string
    }


    @Override
    public EducationalQualification deleteEducationalQualificationById(Long educationalId) {
        EducationalQualification educationalQualificationDelete = educationalQualificationRepo.findById(educationalId).orElse(null);
        if (educationalQualificationDelete != null) {
            educationalQualificationRepo.delete(educationalQualificationDelete);
            return educationalQualificationDelete;
        } else {
            throw new RuntimeException("EducationalQualifications not found with id: " + educationalId);
        }

    }
}
