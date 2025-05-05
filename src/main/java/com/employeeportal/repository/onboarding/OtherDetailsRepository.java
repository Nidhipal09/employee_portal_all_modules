package com.employeeportal.repository.onboarding;

import com.employeeportal.model.onboarding.OtherDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherDetailsRepository extends JpaRepository<OtherDetails, Long> {
    OtherDetails findByEmployeeId(int employeeId);
}