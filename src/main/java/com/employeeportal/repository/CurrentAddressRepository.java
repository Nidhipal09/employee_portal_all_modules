package com.employeeportal.repository;

import com.employeeportal.model.CurrentAddress;
import com.employeeportal.model.PermanentAddress;
import com.employeeportal.model.PrimaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrentAddressRepository  extends JpaRepository<CurrentAddress,Long> {
    Optional<CurrentAddress> findByPrimaryDetails(PrimaryDetails primaryDetails);

    Optional<CurrentAddress> findByCurrentId(Long currentId);
}
