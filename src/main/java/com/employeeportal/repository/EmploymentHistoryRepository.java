package com.employeeportal.repository;

import com.employeeportal.model.EducationalQualification;
import com.employeeportal.model.EmploymentHistory;
import com.employeeportal.model.PrimaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentHistoryRepository  extends JpaRepository<EmploymentHistory,Long> {
    List<EmploymentHistory> findAllByPrimaryDetails(PrimaryDetails primaryDetails);
}
