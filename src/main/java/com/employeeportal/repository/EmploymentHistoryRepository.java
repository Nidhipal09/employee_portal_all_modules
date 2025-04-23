package com.employeeportal.repository;

import com.employeeportal.model.EducationalQualification;
import com.employeeportal.model.EmploymentHistory;
import com.employeeportal.model.PrimaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmploymentHistoryRepository  extends JpaRepository<EmploymentHistory,Long> {
    List<EmploymentHistory> findAllByPrimaryDetails(PrimaryDetails primaryDetails);

    Optional<EmploymentHistory> findByEmploymentId(Long employmentId);
}
