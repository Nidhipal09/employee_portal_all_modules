package com.employeeportal.repository.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employeeportal.model.registration.EmployeeReg;

@Repository
public interface EmployeeRegRepository extends JpaRepository<EmployeeReg, Integer> {

    EmployeeReg findByEmail(String email);

    @Query("SELECT e FROM EmployeeReg e WHERE e.employee.employeeId = :employeeId")
    EmployeeReg findByEmployeeId(@Param("employeeId") int employeeId);

    boolean existsByEmail(String email);

}
