package com.employeeportal.controller;

import com.employeeportal.exception.ResourceNotFoundException;
import com.employeeportal.model.EducationDTO;
import com.employeeportal.model.EducationalQualification;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.dto.EducationResponseDTO;
import com.employeeportal.service.EducationalQualificationService;
import com.employeeportal.service.PrimaryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/educationalQualification")
@CrossOrigin(origins = "*")
public class EducationalQualificationController {
    @Autowired
    private EducationalQualificationService educationalQualificationService;

    @PostMapping("/save")
    public ResponseEntity<EducationResponseDTO> saveEducationalQualification(@RequestBody EducationDTO educationalQualification) {
        EducationResponseDTO addeducationalQualification = educationalQualificationService.saveEducationalQualification(educationalQualification);
        return new ResponseEntity<>(addeducationalQualification, HttpStatus.CREATED);

    }
    @GetMapping("/allEducationalQualifications")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE')")
    public ResponseEntity<List<EducationalQualification>> getAllEducationalQualification() {
        List<EducationalQualification> allEducationalQualification = educationalQualificationService.getAllEducationalQualification();
        return new ResponseEntity<>(allEducationalQualification, HttpStatus.OK);
    }
    @GetMapping("/educational/{educationalId}")
    public ResponseEntity<EducationalQualification> getEducationalQualificationById(@PathVariable("educationalId") Long  educationalId) {
        EducationalQualification educationalQualificationById = educationalQualificationService.getEducationalQualificationById( educationalId);

        if (educationalQualificationById == null) {
            throw new ResourceNotFoundException("User not found with id "   + educationalId);
        }
        return new ResponseEntity<>(educationalQualificationById, HttpStatus.OK);
    }

    @PutMapping("/update/{educationalId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<EducationalQualification> updateEducationalQualificationById(@PathVariable("educationalId") Long educationalId, @RequestBody EducationalQualification educationalQualification) {
        EducationalQualification updateEducationalQualification = educationalQualificationService. updateEducationalQualificationById(educationalId, educationalQualification);
        return new ResponseEntity<>(updateEducationalQualification, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{educationalId}")
    public ResponseEntity<String> deleteEducationalQualificationById(@PathVariable("educationalId") Long educationalId) {
        EducationalQualification isDeleted = educationalQualificationService.deleteEducationalQualificationById(educationalId);
        return new ResponseEntity<>("data deleted", HttpStatus.OK);
    }




}
