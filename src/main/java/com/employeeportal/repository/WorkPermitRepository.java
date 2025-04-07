package com.employeeportal.repository;

import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.WorkPermit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkPermitRepository extends JpaRepository<WorkPermit, Long> {
    Optional<WorkPermit> findByPrimaryDetails(PrimaryDetails primaryDetails);
}
