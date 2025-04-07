package com.employeeportal.controller;

import com.employeeportal.exception.ResourceNotFoundException;
import com.employeeportal.model.EmployeeDetails;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.service.EmployeeDetailsService;
import com.employeeportal.service.PrimaryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeesDetails")
@CrossOrigin(origins = "*")
public class EmployeeDetailsController {
    @Autowired
    private EmployeeDetailsService employeeDetailsService;

    @PostMapping("/save")
    public ResponseEntity<EmployeeDetails> saveEmployeeDetails(@RequestBody EmployeeDetails employeeDetails) {
        EmployeeDetails addemployeeDetails = employeeDetailsService.saveEmployeeDetails(employeeDetails);
        return new ResponseEntity<>(addemployeeDetails, HttpStatus.CREATED);

    }

    @GetMapping("/allEmployeeDetails")

    public ResponseEntity<List<EmployeeDetails>> getAllEmployeeDetails() {
        List<EmployeeDetails> allEmployeeDetails = employeeDetailsService.getAllEmployeeDetails();
        return new ResponseEntity<>(allEmployeeDetails, HttpStatus.OK);
    }
    @GetMapping("/ employeeDetails/{employeeId}")
    public ResponseEntity<EmployeeDetails> getEmployeeDetailsById(@PathVariable("employeeId") Long  employeeId) {
        EmployeeDetails EmployeeDetailsById = employeeDetailsService.getEmployeeDetailsById( employeeId);

        if (EmployeeDetailsById == null) {
            throw new ResourceNotFoundException("User not found with id " +  employeeId);
        }
        return new ResponseEntity<>(EmployeeDetailsById, HttpStatus.OK);
    }
    @PutMapping("/update/{employeeId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<EmployeeDetails> updateEmployeeDetailsById(@PathVariable("employeeId") Long employeeId, @RequestBody EmployeeDetails employeeDetails) {
        EmployeeDetails updateEmployeeDetails = employeeDetailsService. updateEmployeeDetailsById(employeeId, employeeDetails);

        return new ResponseEntity<>(updateEmployeeDetails, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<String> deleteEmployeeDetailsById(@PathVariable("employeeId") Long employeeId) {
        EmployeeDetails isDeleted = employeeDetailsService.deleteEmployeeDetailsById(employeeId);
        return new ResponseEntity<>("data deleted", HttpStatus.OK);
    }
}
