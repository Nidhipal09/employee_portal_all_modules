package com.employeeportal.repository;

import com.employeeportal.model.JwtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//import java.util.Optional;
@Repository
public interface JwtRepository extends JpaRepository<JwtEntity, Long> {

  JwtEntity findByJtiAndValidSession(String jti, boolean validSession);

}
