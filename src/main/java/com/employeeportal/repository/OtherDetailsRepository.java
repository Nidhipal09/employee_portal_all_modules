package com.employeeportal.repository;

import com.employeeportal.model.OtherDetails;
import com.employeeportal.model.PassportDetails;
import com.employeeportal.model.PrimaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtherDetailsRepository extends JpaRepository<OtherDetails,Long> {
    Optional<OtherDetails> findByPrimaryDetails(PrimaryDetails primaryDetails);

    Optional<OtherDetails> findByOtherId(Long otherId);
}
