package com.employeeportal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "leave_balance")
public class LeaveBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;
    private Long primaryId; // assuming leave is tracked per employee
    private String leaveType;
    private int availableDays;
    private int consumedDays;
    private int carryoverDays;
    private double annualQuota;
}
