package com.employeeportal.controller;

import com.employeeportal.model.dto.LeaveBalanceDTO;
import com.employeeportal.service.LeaveBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/leaves")
public class LeaveBalanceController {
    @Autowired
    private LeaveBalanceService leaveBalanceService;

    @PostMapping("/balance")
    public String saveLeaveBalance(@RequestBody LeaveBalanceDTO dto) {
        leaveBalanceService.saveLeaveBalance(dto);
        return "Leave balance saved successfully";
    }
//    @GetMapping("/balance/{primaryId}")
//    public List<LeaveBalanceDTO> getLeaveBalances(@PathVariable String primaryId) {
//        return leaveBalanceService.getLeaveBalances(primaryId);
//    }
@GetMapping("/balance/{primaryId}/summary")
public Map<String, Object> getSummary(@PathVariable String primaryId) {
    double totalAvailable = leaveBalanceService.getTotalAvailableLeaves(primaryId);
    List<LeaveBalanceDTO> balances = leaveBalanceService.getLeaveBalances(primaryId);

    Map<String, Object> response = new HashMap<>();
    response.put("totalAvailableLeaveDays", totalAvailable);
    response.put("leaveDetails", balances);

    return response;
}

}
