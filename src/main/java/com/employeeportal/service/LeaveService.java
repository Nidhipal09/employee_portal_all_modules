package com.employeeportal.service;

import com.employeeportal.model.Leave;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.dto.LeaveDTO;
import com.employeeportal.model.dto.ReportingManagerDTO;
import com.employeeportal.model.dto.ReportingManagerResponseDTO;

import java.util.List;

public interface LeaveService {
    Leave applyLeave(LeaveDTO request);
    List<ReportingManagerResponseDTO> getAllReportingManagers();

}
