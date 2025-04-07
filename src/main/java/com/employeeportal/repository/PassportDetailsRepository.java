package com.employeeportal.repository;

import com.employeeportal.model.PassportDetails;
import com.employeeportal.model.PermanentAddress;
import com.employeeportal.model.PrimaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassportDetailsRepository  extends JpaRepository<PassportDetails,Long> {
    Optional<PassportDetails> findByPrimaryDetails(PrimaryDetails primaryDetails);
}
