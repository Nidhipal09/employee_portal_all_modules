package com.employeeportal.repository.onboarding;

import com.employeeportal.model.onboarding.VisaDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisaDetailsRepository extends JpaRepository<VisaDetails, Long> {
    VisaDetails findByEmployeeEmployeeId(int employeeId);
}