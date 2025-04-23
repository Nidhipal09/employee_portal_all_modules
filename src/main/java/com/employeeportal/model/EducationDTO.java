package com.employeeportal.model;

import com.employeeportal.model.dto.DocumentCertificateDTO;
import com.employeeportal.model.dto.EducationalQualificationDTO;
import com.employeeportal.model.dto.EmploymentHistoryDTO;
import lombok.Data;

import java.util.List;
@Data
public class EducationDTO {
    private Long primaryId;
    private List<EducationalQualificationDTO> educationalQualifications;
    private List<DocumentCertificateDTO> documents;
    private List<EmploymentHistoryDTO> employmentHistories;
}
