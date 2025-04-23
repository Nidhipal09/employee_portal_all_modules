package com.employeeportal.service;

import com.employeeportal.model.EducationDTO;
import com.employeeportal.model.EducationalQualification;
import com.employeeportal.model.dto.EducationResponseDTO;

import java.util.List;

public interface EducationalQualificationService {
    EducationResponseDTO saveEducationalQualification(EducationDTO educationalQualification);
    List<EducationalQualification> getAllEducationalQualification();
    EducationalQualification getEducationalQualificationById(Long educationalId);
    EducationalQualification updateEducationalQualificationById(Long educationalId, EducationalQualification educationalQualification);
    EducationalQualification deleteEducationalQualificationById(Long educationalId);

    EducationResponseDTO updateEducationalQualification(EducationDTO educationalQualification);
}
