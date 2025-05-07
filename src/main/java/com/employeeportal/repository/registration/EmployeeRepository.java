package com.employeeportal.repository.registration;

import com.employeeportal.model.GeneralResponses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.employeeportal.model.registration.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

  Employee findByEmployeeId(int employeeId);

  @Query(value = "select primary_id,role_name from employee  where email = ?1", nativeQuery = true)
  GeneralResponses getIdByEmployeename(String employeeName);
  
  @Query(value = "SELECT * FROM employee WHERE email = ?1", nativeQuery = true)
  Employee findByEmail(String email);

  Employee findByMobileNumber(String mobileNumber);

  @Transactional
  @Modifying
  @Query(value = "UPDATE employee SET password = ?2 WHERE email = ?1", nativeQuery = true)
  void updatePasswordByEmail(String email, String newPassword);

  boolean existsByEmail(String email);

  @Query(value = "SELECT password FROM employee WHERE email = :email", nativeQuery = true)
  String findPasswordByEmail(String email);

  // List<PrimaryDetails> findByRoleName(String roleName);

}
