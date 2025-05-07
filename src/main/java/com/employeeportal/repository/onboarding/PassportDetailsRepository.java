package com.employeeportal.repository.onboarding;

import com.employeeportal.model.onboarding.PassportDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportDetailsRepository extends JpaRepository<PassportDetails, Long> {

    PassportDetails findByPassportNumber(String passportNumber);
    PassportDetails findByEmployeeEmployeeId(int employeeId);
}