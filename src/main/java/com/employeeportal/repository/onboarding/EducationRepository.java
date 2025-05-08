package com.employeeportal.repository.onboarding;

import com.employeeportal.model.onboarding.Education;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findByEmployeeEmployeeId(int employeeId);

    @Modifying
    @Transactional
    @Query(value = "delete from education where employee_id = ?1", nativeQuery = true)
    void deleteAllByEmployeeId(int employeeId);
}