package com.employeeportal.repository;

import com.employeeportal.model.EmployeeRelative;
import com.employeeportal.model.PrimaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRelativeRepository extends JpaRepository<EmployeeRelative, Long> {

    Optional<EmployeeRelative> findByPrimaryDetails(PrimaryDetails primaryDetails);

    Optional<EmployeeRelative> findByEmployeeRelativeId(Long employeeRelativeId);
}