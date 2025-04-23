package com.employeeportal.service;

import com.employeeportal.model.dto.LeaveBalanceDTO;

import java.util.List;

public interface LeaveBalanceService {
   // List<LeaveBalanceDTO> getLeaveBalances(String primaryId);
   double getTotalAvailableLeaves(String primaryId);

    void saveLeaveBalance(LeaveBalanceDTO dto);

    List<LeaveBalanceDTO> getLeaveBalances(String primaryId);
}