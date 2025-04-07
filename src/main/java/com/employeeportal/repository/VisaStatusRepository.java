package com.employeeportal.repository;

import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.VisaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisaStatusRepository extends JpaRepository<VisaStatus, Long> {
    Optional<VisaStatus> findByPrimaryDetails(PrimaryDetails primaryDetails);
    // You can add custom query methods if needed
}