package com.employeeportal.repository;

import com.employeeportal.model.Leave;
import com.employeeportal.model.OtherDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Long> {
}
