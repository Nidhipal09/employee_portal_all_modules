package com.employeeportal.repository;

import com.employeeportal.model.EmployeeDetails;
import com.employeeportal.model.PrimaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails,Long>  {
//
//    static Optional<EmployeeDetails> findByPrimaryDetails(PrimaryDetails primaryDetails) {
//    }
}
