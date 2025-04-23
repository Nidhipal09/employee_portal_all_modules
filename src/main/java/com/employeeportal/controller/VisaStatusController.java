package com.employeeportal.controller;

import com.employeeportal.exception.ResourceNotFoundException;
import com.employeeportal.model.CurrentAddress;
import com.employeeportal.model.VisaStatus;
import com.employeeportal.service.VisaStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/visa-status")
@CrossOrigin(origins = "*")
public class VisaStatusController {
    @Autowired
    private  VisaStatusService visaStatusService;

    @PostMapping("/save")
    public ResponseEntity<VisaStatus> createVisaStatus(@RequestBody VisaStatus visaStatus) {
        VisaStatus savedVisaStatus = visaStatusService.saveVisaStatus(visaStatus);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVisaStatus);
    }

    @GetMapping("/visa/{visaId}")
    public ResponseEntity<VisaStatus> getVisaStatusById(@PathVariable("visaId") Long visaId) {
        VisaStatus visaStatusById = visaStatusService.getVisaStatusById(visaId);
        if(visaStatusById == null){
            throw new ResourceNotFoundException("User not found with id " + visaId);
        }
       return new ResponseEntity<>(visaStatusById,HttpStatus.OK);
    }
    @GetMapping("/allCurrentAddress")
    public ResponseEntity<List<VisaStatus>> getAllVisaStatus() {
        List<VisaStatus> allVisaStatus = visaStatusService.getAllVisaStatus();
        return new ResponseEntity<>(allVisaStatus, HttpStatus.OK);
    }

    @PutMapping("/update/{visaId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<VisaStatus> updateVisaStatus(@PathVariable Long visaId, @RequestBody VisaStatus visaStatus) {
        VisaStatus updatedVisaStatus = visaStatusService.updateVisaStatusById(visaId, visaStatus);
        return new ResponseEntity<>(updatedVisaStatus, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{visaId}")
    public ResponseEntity<String> deleteVisaStatusById(@PathVariable("currentId") Long visaId) {
        VisaStatus isDeleted = visaStatusService.deleteVisaStatusById(visaId);
        return new ResponseEntity<>("data deleted", HttpStatus.OK);
    }

}
