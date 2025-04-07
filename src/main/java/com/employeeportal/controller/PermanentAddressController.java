package com.employeeportal.controller;

import com.employeeportal.exception.ResourceNotFoundException;
import com.employeeportal.model.PermanentAddress;
import com.employeeportal.model.PersonalDetails;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.service.PermanentAddressService;
import com.employeeportal.service.PrimaryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permanentAddress")
@CrossOrigin(origins = "*")
public class PermanentAddressController {
    @Autowired
    private PermanentAddressService permanentAddressService;
    @PostMapping("/save")
    public ResponseEntity<PermanentAddress> savePermanentAddress(@RequestBody PermanentAddress permanentAddress) {
        PermanentAddress addPermanentAddress = permanentAddressService.savePermanentAddress(permanentAddress);
        return new ResponseEntity<>(addPermanentAddress, HttpStatus.CREATED);

    }
    @GetMapping("/allPermanentAddress")

    public ResponseEntity<List<PermanentAddress>> getAllPermanentAddress() {
        List<PermanentAddress> allPermanentAddress = permanentAddressService.getAllPermanentAddress();
        return new ResponseEntity<>(allPermanentAddress, HttpStatus.OK);
    }

    @GetMapping("/permanent/{permanentId}")
    public ResponseEntity<PermanentAddress> getPermanentAddressById(@PathVariable("permanentId") Long  permanentId) {
        PermanentAddress PermanentAddressById = permanentAddressService.getPermanentAddressById(permanentId);

        if (PermanentAddressById == null) {
            throw new ResourceNotFoundException("User not found with id " + permanentId);
        }
        return new ResponseEntity<>(PermanentAddressById, HttpStatus.OK);
    }
    @PutMapping("/update/{permanentId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<PermanentAddress> updatePermanentAddressById(@PathVariable("permanentId") Long permanentId, @RequestBody PermanentAddress permanentAddress) {
        PermanentAddress updatePermanentAddress = permanentAddressService. updatePermanentAddressById(permanentId, permanentAddress);

        return new ResponseEntity<>(updatePermanentAddress, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{permanentId}")
    public ResponseEntity<String> deletePermanentAddressById(@PathVariable("permanentId") Long permanentId) {
        PermanentAddress isDeleted = permanentAddressService.deletePermanentAddressById(permanentId);
        return new ResponseEntity<>("data deleted", HttpStatus.OK);
    }
}

