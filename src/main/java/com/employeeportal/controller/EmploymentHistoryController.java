package com.employeeportal.controller;

import com.employeeportal.exception.ResourceNotFoundException;
import com.employeeportal.model.EmploymentHistory;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.service.EmploymentHistoryService;
import com.employeeportal.service.PrimaryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employmentHistory")
@CrossOrigin(origins = "*")
public class EmploymentHistoryController {
    @Autowired
    private EmploymentHistoryService  employmentHistoryService ;

    @PostMapping("/save")
    public ResponseEntity<EmploymentHistory> saveEmploymentHistory(@RequestBody EmploymentHistory employmentHistory) {
        EmploymentHistory addemploymentHistory = employmentHistoryService.saveEmploymentHistory(employmentHistory);
        return new ResponseEntity<>(addemploymentHistory, HttpStatus.CREATED);

    }

    @GetMapping("/allEmploymentHistory")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE')")
    public ResponseEntity<List<EmploymentHistory>> getAllEmploymentHistory() {
        List<EmploymentHistory> allEmploymentHistory = employmentHistoryService.getAllEmploymentHistory();
        return new ResponseEntity<>(allEmploymentHistory, HttpStatus.OK);
    }

    @GetMapping("/employment/{employmentId}")
    public ResponseEntity<EmploymentHistory> getEmploymentHistoryById(@PathVariable("employmentId") Long  employmentId) {
        EmploymentHistory employmentHistoryById = employmentHistoryService.getEmploymentHistoryById( employmentId);

        if (employmentHistoryById == null) {
            throw new ResourceNotFoundException("User not found with id " +  employmentId);
        }
        return new ResponseEntity<>(employmentHistoryById, HttpStatus.OK);
    }
    @PutMapping("/update/{employmentId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<EmploymentHistory> updateEmploymentHistoryById(@PathVariable("employmentId") Long employmentId, @RequestBody EmploymentHistory employmentHistory) {
        EmploymentHistory updateEmploymentHistory = employmentHistoryService.updateEmploymentHistoryById(employmentId, employmentHistory);
        // return ResponseEntity.ok(updateUser);
        return new ResponseEntity<>(updateEmploymentHistory, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{employmentId}")
    public ResponseEntity<String> deleteEmploymentHistoryById(@PathVariable("employmentId") Long employmentId) {
        EmploymentHistory isDeleted = employmentHistoryService.deleteEmploymentHistoryById(employmentId);
        return new ResponseEntity<>("data deleted", HttpStatus.OK);
    }

}
