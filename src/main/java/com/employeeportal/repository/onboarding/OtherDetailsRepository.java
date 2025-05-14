package com.employeeportal.repository.onboarding;

import com.employeeportal.model.onboarding.AdditionalDetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherDetailsRepository extends JpaRepository<AdditionalDetails, Long> {
    AdditionalDetails findByEmployeeEmployeeId(int employeeId);
}