package com.employeeportal.controller;

import com.employeeportal.exception.ResourceNotFoundException;
import com.employeeportal.model.EmployeeDetails;
import com.employeeportal.model.PersonalDetails;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.service.EmployeeDetailsService;
import com.employeeportal.service.PersonalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personalDetails")
@CrossOrigin(origins = "*")
public class PersonalDetailsController {
    @Autowired
    private PersonalDetailsService personalDetailsService;
    @PostMapping("/save")
    public ResponseEntity<PersonalDetails> savePersonalDetails(@RequestBody PersonalDetails personalDetails) {
        PersonalDetails addPersonalDetails = personalDetailsService.savePersonalDetails(personalDetails);
        return new ResponseEntity<>(addPersonalDetails, HttpStatus.CREATED);

    }
    @GetMapping("/allPersonalDetails")

    public ResponseEntity<List<PersonalDetails>> getAllPersonalDetails() {
        List<PersonalDetails> allPersonalDetails = personalDetailsService.getAllPersonalDetails();
        return new ResponseEntity<>(allPersonalDetails, HttpStatus.OK);
    }
    @GetMapping("/personalDetail/{personalId}")
    public ResponseEntity<PersonalDetails> getPersonalDetailsById(@PathVariable("personalId") Long  personalId) {
        PersonalDetails personalDetailsById = personalDetailsService.getPersonalDetailsById( personalId);

        if (personalDetailsById == null) {
            throw new ResourceNotFoundException("User not found with id " +  personalId);
        }
        return new ResponseEntity<>(personalDetailsById, HttpStatus.OK);
    }
    @PutMapping("/update/{personalId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<PersonalDetails> updatePersonalDetailsById(@PathVariable("personalId") Long personalId, @RequestBody PersonalDetails personalDetails) {
        PersonalDetails updatePersonalDetails = personalDetailsService. updatePersonalDetailsById(personalId, personalDetails);

        return new ResponseEntity<>(updatePersonalDetails, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{personalId}")
    public ResponseEntity<String> deletePersonalDetailsById(@PathVariable("personalId") Long personalId) {
        PersonalDetails isDeleted = personalDetailsService.deletePersonalDetailsById(personalId);
        return new ResponseEntity<>("data deleted", HttpStatus.OK);
    }


}
