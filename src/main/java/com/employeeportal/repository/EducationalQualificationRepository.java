package com.employeeportal.repository;

import com.employeeportal.model.AddressDetails;
import com.employeeportal.model.EducationalQualification;
import com.employeeportal.model.PrimaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationalQualificationRepository  extends JpaRepository<EducationalQualification,Long> {
    List<EducationalQualification> findAllByPrimaryDetails(PrimaryDetails primaryDetails);
}
