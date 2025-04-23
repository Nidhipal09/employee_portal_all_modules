package com.employeeportal.serviceImpl;

import com.employeeportal.model.LeaveBalance;
import com.employeeportal.model.dto.LeaveBalanceDTO;
import com.employeeportal.repository.LeaveBalanceRepository;
import com.employeeportal.service.LeaveBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveBalanceServiceImpl implements LeaveBalanceService {

    @Autowired
    private LeaveBalanceRepository leaveBalanceRepository;


    @Override
    public void saveLeaveBalance(LeaveBalanceDTO dto) {
        LeaveBalance entity = new LeaveBalance();
        entity.setPrimaryId(dto.getPrimaryId());
        entity.setLeaveType(dto.getLeaveType());
        entity.setAvailableDays(dto.getAvailableDays());
        entity.setConsumedDays(dto.getConsumedDays());
        entity.setCarryoverDays(dto.getCarryoverDays());
        entity.setAnnualQuota(dto.getAnnualQuota());

        leaveBalanceRepository.save(entity);
    }


        @Override
    public List<LeaveBalanceDTO> getLeaveBalances(String primaryId) {
        List<LeaveBalance> balances = leaveBalanceRepository.findByPrimaryId(Long.valueOf(primaryId));
        List<LeaveBalanceDTO> result = new ArrayList<LeaveBalanceDTO>();

        for (LeaveBalance balance : balances) {
            LeaveBalanceDTO dto = new LeaveBalanceDTO();
            dto.setPrimaryId(balance.getPrimaryId());
            dto.setLeaveType(balance.getLeaveType()); // ✅ No conversion
            dto.setAvailableDays(balance.getAvailableDays());
            dto.setConsumedDays(balance.getConsumedDays());
            dto.setCarryoverDays(balance.getCarryoverDays());
            dto.setAnnualQuota(balance.getAnnualQuota());

            result.add(dto);
        }

        return result;
    }
@Override
public double getTotalAvailableLeaves(String primaryId) {
    List<LeaveBalance> balances = leaveBalanceRepository.findByPrimaryId(Long.valueOf(primaryId));

    double totalAvailable = 0;
    for (LeaveBalance balance : balances) {
        double available = balance.getCarryoverDays() + balance.getAnnualQuota() - balance.getConsumedDays();
        totalAvailable += available;
    }

    return totalAvailable;
}

}