package com.employeeportal.model.dto;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
public class LeaveDTO {
    @NotNull
    private LocalDate fromDate;

    @NotNull
    private LocalDate toDate;

    @NotBlank
    private String leaveType;
    @NotBlank
    private String reason;

    @NotNull
    private Long reportingManagerId; // selected from dropdown
    private boolean notifyManager;
    private Long primaryId;
    @Enumerated(EnumType.STRING)
    private DayOfLeave dayOfLeave;


}
