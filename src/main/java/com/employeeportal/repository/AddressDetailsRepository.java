package com.employeeportal.repository;

import com.employeeportal.model.AddressDetails;
import com.employeeportal.model.CurrentAddress;
import com.employeeportal.model.PrimaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressDetailsRepository extends JpaRepository<AddressDetails,Long> {
    List<AddressDetails> findByPrimaryDetails(PrimaryDetails obj);

    Optional<AddressDetails> findByAddressId(Long addressId);
}
