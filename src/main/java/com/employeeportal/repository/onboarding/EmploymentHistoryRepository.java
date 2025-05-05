package com.employeeportal.repository.onboarding;

import com.employeeportal.model.onboarding.EmploymentHistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentHistoryRepository extends JpaRepository<EmploymentHistory, Long> {
    List<EmploymentHistory> findByEmployeeId(int employeeId);
}