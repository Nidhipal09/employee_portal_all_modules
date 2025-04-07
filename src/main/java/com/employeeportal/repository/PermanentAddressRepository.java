package com.employeeportal.repository;

import com.employeeportal.model.PermanentAddress;
import com.employeeportal.model.PersonalDetails;
import com.employeeportal.model.PrimaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermanentAddressRepository  extends JpaRepository<PermanentAddress,Long> {
    Optional<PermanentAddress> findByPrimaryDetails(PrimaryDetails primaryDetails);
}
