package com.employeeportal.repository.onboarding;

import com.employeeportal.model.onboarding.IdentificationDetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IdentificationDetailsRepository extends JpaRepository<IdentificationDetails, Long> {
    List<IdentificationDetails> findByEmployeeEmployeeId(int employeeId);

    @Modifying
    @Transactional
    @Query(value = "delete from identification_details where employee_id = ?1", nativeQuery = true)
    void deleteAllByEmployeeId(int employeeId);
}