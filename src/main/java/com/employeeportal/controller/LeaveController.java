package com.employeeportal.controller;

import com.employeeportal.model.Leave;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.dto.LeaveDTO;
import com.employeeportal.model.dto.ReportingManagerDTO;
import com.employeeportal.model.dto.ReportingManagerResponseDTO;
import com.employeeportal.repository.PrimaryDetailsRepository;
import com.employeeportal.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/leave")
@CrossOrigin(origins = "*")
@Validated
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping("/apply")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE')")
    public ResponseEntity<?> applyLeave(@Valid @RequestBody LeaveDTO request) {
        Leave leave = leaveService.applyLeave(request);
        return ResponseEntity.ok(leave);
    }

    @GetMapping("/reporting-managers")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE')")
    public ResponseEntity<?> getReportingManagers() {
        List<ReportingManagerResponseDTO> managers = leaveService.getAllReportingManagers();
        return ResponseEntity.ok(managers);
    }


}
