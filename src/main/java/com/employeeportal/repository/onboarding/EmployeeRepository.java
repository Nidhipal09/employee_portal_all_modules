package com.employeeportal.repository.onboarding;

import com.employeeportal.model.GeneralResponses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.onboarding.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

  @Query(value = "select primary_id,role_name from employee  where email = ?1", nativeQuery = true)
  GeneralResponses getIdByEmployeename(String employeeName);

  @Query("SELECT u FROM employee u WHERE u.email = ?1")
  Employee findByEmail(String email);

  Employee findByMobileNumber(String mobileNumber);

  @Query("UPDATE employee u SET u.password = ?2 WHERE u.email = ?1")
  void updatePasswordByEmail(String token, String newPassword);

  boolean existsByEmail(String email);

  @Query("SELECT u.password FROM employee u WHERE u.email = :email")
  String findPasswordByEmail(String email);

  // List<PrimaryDetails> findByRoleName(String roleName);

}
