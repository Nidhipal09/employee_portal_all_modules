package com.employeeportal.repository.onboarding;

import com.employeeportal.model.onboarding.IdentificationDetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentificationDetailsRepository extends JpaRepository<IdentificationDetails, Long> {
    List<IdentificationDetails> findByEmployeeEmployeeId(int employeeId);
}