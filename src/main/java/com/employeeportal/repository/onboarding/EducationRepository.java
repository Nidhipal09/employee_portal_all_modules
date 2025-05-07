package com.employeeportal.repository.onboarding;

import com.employeeportal.model.onboarding.Education;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findByEmployeeEmployeeId(int employeeId);
}