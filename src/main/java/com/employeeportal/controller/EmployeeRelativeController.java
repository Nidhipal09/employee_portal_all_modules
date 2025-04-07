package com.employeeportal.controller;

import com.employeeportal.model.CurrentAddress;
import com.employeeportal.model.EmployeeRelative;
import com.employeeportal.model.dto.EmployeeRelativeDTO;
import com.employeeportal.service.EmployeeRelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee-relatives")
@CrossOrigin(origins = "*") // Allowing cross-origin requests if needed
public class EmployeeRelativeController {

    @Autowired
    private EmployeeRelativeService employeeRelativeService;

    // Save Employee Relative
    @PostMapping("/save")
    public ResponseEntity<EmployeeRelative> saveEmployeeRelative(@RequestBody EmployeeRelative employeeRelative) {
        EmployeeRelative savedRelative = employeeRelativeService.saveEmployeeRelative(employeeRelative);
        return  new ResponseEntity<>(savedRelative, HttpStatus.OK);
    }

    // Get All Employee Relatives
    @GetMapping("/allEmployeeRelatives")
    public ResponseEntity<List<EmployeeRelative>> getAllEmployeeRelatives() {
        List<EmployeeRelative> relatives = employeeRelativeService.getAllEmployeeRelatives();
        return ResponseEntity.ok(relatives);
    }

    @GetMapping("/{employeeRelativeId}")
    public ResponseEntity<EmployeeRelative> getEmployeeRelativeById(@PathVariable Long employeeRelativeId) {
        EmployeeRelative relativeDTO = employeeRelativeService.getEmployeeRelativeById(employeeRelativeId);
        return ResponseEntity.ok(relativeDTO);
    }
    @PutMapping("/update/{employeeRelativeId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<EmployeeRelative> updateCurrentAddressById(@PathVariable("employeeRelativeId") Long employeeRelativeId,@RequestBody EmployeeRelative employeeRelative){
EmployeeRelative updateEmployeeRelative = employeeRelativeService.updateEmployeeRelativeById(employeeRelativeId,employeeRelative);
return new ResponseEntity<>(updateEmployeeRelative,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{employeeRelativeId}")
    public ResponseEntity<String> deleteEmployeeRelativeById(@PathVariable("currentId") Long employeeRelativeId) {
        EmployeeRelative isDeleted = employeeRelativeService.deleteEmployeeRelativeById(employeeRelativeId);
        return new ResponseEntity<>("data deleted", HttpStatus.OK);
    }
}