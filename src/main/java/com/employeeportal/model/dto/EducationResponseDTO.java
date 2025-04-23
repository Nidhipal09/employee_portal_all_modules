package com.employeeportal.model.dto;

import com.employeeportal.model.DocumentCertificates;
import com.employeeportal.model.EducationalQualification;
import com.employeeportal.model.EmploymentHistory;
import lombok.Data;

import java.util.List;

@Data
public class EducationResponseDTO {
    private List<EducationalQualification> educationalQualificationList;
    private List<DocumentCertificates> documentCertificates;
    private List<EmploymentHistory> employmentHistories;

}
