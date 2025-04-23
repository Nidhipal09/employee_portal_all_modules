package com.employeeportal.model;

import com.employeeportal.model.dto.DayOfLeave;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "leaves")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String leaveType;
    private String reason;
    private Long reportingManagerId;  // Still needed (from dropdown)
    private int totalLeaveDays;
    private boolean notifyManager;
    @Enumerated(EnumType.STRING)
    private StatusType statusType;
    @Enumerated(EnumType.STRING)
    private DayOfLeave dayOfLeave;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "primary_id", nullable = false)
    private PrimaryDetails primaryDetails;

}
