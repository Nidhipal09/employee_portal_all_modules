package com.employeeportal.repository.onboarding;

import com.employeeportal.model.onboarding.Address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByEmployeeEmployeeId(int employeeId);

    @Modifying
    @Transactional
    @Query(value = "delete from address where employee_id = ?1", nativeQuery = true)
    void deleteAllByEmployeeId(int employeeId);
}