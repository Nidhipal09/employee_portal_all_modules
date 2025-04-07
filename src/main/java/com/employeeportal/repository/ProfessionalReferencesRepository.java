package com.employeeportal.repository;

import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.ProfessionalReferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionalReferencesRepository   extends JpaRepository<ProfessionalReferences,Long> {
    List<ProfessionalReferences> findAllByPrimaryDetails(PrimaryDetails primaryDetails);
}
