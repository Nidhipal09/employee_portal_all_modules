package com.employeeportal.repository;

import com.employeeportal.model.GeneralResponses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employeeportal.model.PrimaryDetails;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrimaryDetailsRepository extends JpaRepository<PrimaryDetails,Long>{

  //  Employee findByEmployeeEmail(String employeeName);

    @Query(value = "select primary_id,role_name from primary_details where email = ?1", nativeQuery = true)
    GeneralResponses getIdByEmployeename(String employeeName);
    @Query("SELECT u FROM PrimaryDetails u WHERE u.email = ?1")
   PrimaryDetails findByEmail(String email);
    PrimaryDetails findByMobileNumber(String mobileNumber);
    @Query("UPDATE PrimaryDetails u SET u.password = ?2 WHERE u.email = ?1")
    void updatePasswordByEmail(String token, String newPassword);

    boolean existsByEmail(String email);

    @Query("SELECT u.password FROM PrimaryDetails u WHERE u.email = :email")
    String findPasswordByEmail(String email);

    List<PrimaryDetails> findByRoleName(String roleName);


}
