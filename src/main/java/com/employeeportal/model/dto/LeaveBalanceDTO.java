package com.employeeportal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveBalanceDTO {
    private Long primaryId;
    private String leaveType;
    private int availableDays;
    private int consumedDays;
    private int carryoverDays;
    private double annualQuota;


}
