package com.employeeportal.repository;

import com.employeeportal.model.LeaveBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {
    List<LeaveBalance> findByPrimaryId(Long primaryId); // fetch all leaves types of the given employee
}
