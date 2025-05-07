package com.employeeportal.repository.onboarding;

import com.employeeportal.model.onboarding.ProfessionalReferences;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalReferencesRepository extends JpaRepository<ProfessionalReferences, Long> {
    List<ProfessionalReferences> findByEmployeeEmployeeId(int employeeId);
}