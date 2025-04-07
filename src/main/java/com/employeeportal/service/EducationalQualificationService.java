package com.employeeportal.service;

import com.employeeportal.model.EducationalQualification;

import java.util.List;

public interface EducationalQualificationService {
    EducationalQualification saveEducationalQualification(EducationalQualification educationalQualification);
    List<EducationalQualification> getAllEducationalQualification();
    EducationalQualification getEducationalQualificationById(Long educationalId);
    EducationalQualification updateEducationalQualificationById(Long educationalId, EducationalQualification educationalQualification);
    EducationalQualification deleteEducationalQualificationById(Long educationalId);
}
