package com.employeeportal.controller;

import com.employeeportal.exception.ResourceNotFoundException;
import com.employeeportal.model.PassportDetails;
import com.employeeportal.model.PermanentAddress;
import com.employeeportal.model.PersonalDetails;
import com.employeeportal.service.PassportDetailsService;
import com.employeeportal.service.PermanentAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passportDetails")
@CrossOrigin(origins = "*")
public class PassportDetailsController {
    @Autowired
    private PassportDetailsService passportDetailsService ;

    @PostMapping("/save")
    public ResponseEntity<PassportDetails> savePassportDetails(@RequestBody PassportDetails passportDetails) {
        PassportDetails addPassportDetails = passportDetailsService.savePassportDetails(passportDetails);
        return new ResponseEntity<>(addPassportDetails, HttpStatus.CREATED);

    }

    @GetMapping("/allPassportDetails")

    public ResponseEntity<List<PassportDetails>> getAllPassportDetails() {
        List<PassportDetails> allPassportDetails =passportDetailsService.getAllPassportDetails();
        return new ResponseEntity<>(allPassportDetails, HttpStatus.OK);
    }

    @GetMapping("/passport/{passportId}")
    public ResponseEntity<PassportDetails> getPassportDetailsById(@PathVariable("passportId") Long  passportId) {
        PassportDetails PassportDetailsById = passportDetailsService.getPassportDetailsById(passportId);

        if (PassportDetailsById == null) {
            throw new ResourceNotFoundException("User not found with id " + passportId);
        }
        return new ResponseEntity<>(PassportDetailsById, HttpStatus.OK);
    }
    @PutMapping("/update/{passportId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<PassportDetails> updatePassportDetailsById(@PathVariable("passportId") Long passportId, @RequestBody PassportDetails  passportDetails) {
        PassportDetails updatePassportDetails = passportDetailsService. updatePassportDetailsById(passportId, passportDetails);

        return new ResponseEntity<>(updatePassportDetails, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{passportId}")
    public ResponseEntity<String> deletePassportDetailsById(@PathVariable("permanentId") Long passportId) {
        PassportDetails isDeleted = passportDetailsService.deletePassportDetailsById(passportId);
        return new ResponseEntity<>("data deleted", HttpStatus.OK);
    }

}
