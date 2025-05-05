package com.employeeportal.repository.onboarding;

import com.employeeportal.model.onboarding.Relatives;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelativesRepository extends JpaRepository<Relatives, Long> {
    List<Relatives> findByEmployeeId(int employeeId);
}