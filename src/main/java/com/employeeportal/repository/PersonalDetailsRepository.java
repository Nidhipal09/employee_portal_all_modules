package com.employeeportal.repository;

import com.employeeportal.model.PersonalDetails;
import com.employeeportal.model.PrimaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalDetailsRepository   extends JpaRepository<PersonalDetails,Long> {
    Optional<PersonalDetails> findByPrimaryDetails(PrimaryDetails objs);
}
