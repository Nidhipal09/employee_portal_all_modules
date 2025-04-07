package com.employeeportal.repository;

import com.employeeportal.model.PersonalDetails;
import com.employeeportal.model.Preview;
import com.employeeportal.model.PrimaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PreviewRepository   extends JpaRepository<Preview,Long> {
    Optional<Preview> findByPrimaryDetails(PrimaryDetails primaryDetails);
}
