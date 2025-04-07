package com.employeeportal.service;

import com.employeeportal.model.EducationalQualification;
import com.employeeportal.model.EmploymentHistory;

import java.util.List;

public interface EmploymentHistoryService {

    EmploymentHistory saveEmploymentHistory(EmploymentHistory employmentHistory);
    List<EmploymentHistory> getAllEmploymentHistory();
    EmploymentHistory getEmploymentHistoryById(Long employmentId);
    EmploymentHistory updateEmploymentHistoryById(Long employmentId, EmploymentHistory employmentHistory);
    EmploymentHistory deleteEmploymentHistoryById(Long employmentId);
}
