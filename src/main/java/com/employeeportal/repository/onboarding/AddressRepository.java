package com.employeeportal.repository.onboarding;

import com.employeeportal.model.onboarding.Address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByEmployeeEmployeeId(int employeeId);
}