package com.employeeportal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.employeeportal.exception.ResourceNotFoundException;
import com.employeeportal.model.CurrentAddress;
import com.employeeportal.model.PermanentAddress;
import com.employeeportal.service.CurrentAddressService;
import com.employeeportal.service.PermanentAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/currentAddress")
@CrossOrigin(origins = "*")
public class CurrentAddressController {
    @Autowired
    private CurrentAddressService currentAddressService;

    @PostMapping("/save")
    public ResponseEntity<CurrentAddress> saveCurrentAddress(@RequestBody CurrentAddress currentAddress) {
        CurrentAddress addCurrentAddress = currentAddressService.saveCurrentAddress(currentAddress);
        return new ResponseEntity<>(addCurrentAddress, HttpStatus.CREATED);

    }

    @GetMapping("/current/{currentId}")
    public ResponseEntity<CurrentAddress> getCurrentAddressById(@PathVariable("currentId") Long currentId) {
        CurrentAddress CurrentAddressById = currentAddressService.getCurrentAddressById(currentId);

        if (CurrentAddressById == null) {
            throw new ResourceNotFoundException("User not found with id " + currentId);
        }
        return new ResponseEntity<>(CurrentAddressById, HttpStatus.OK);
    }
    @GetMapping("/allCurrentAddress")

    public ResponseEntity<List<CurrentAddress>> getAllCurrentAddress() {
        List<CurrentAddress> allCurrentAddress = currentAddressService.getAllCurrentAddress();
        return new ResponseEntity<>(allCurrentAddress, HttpStatus.OK);
    }
    @PutMapping("/update/{currentId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<CurrentAddress> updateCurrentAddressById(@PathVariable("currentId") Long currentId, @RequestBody CurrentAddress currentAddress) {
        CurrentAddress updateCurrentAddress = currentAddressService.updateCurrentAddressById(currentId, currentAddress);

        return new ResponseEntity<>(updateCurrentAddress, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{currentId}")
    public ResponseEntity<String> deleteCurrentAddressById(@PathVariable("currentId") Long currentId) {
        CurrentAddress isDeleted = currentAddressService.deleteCurrentAddressById(currentId);
        return new ResponseEntity<>("data deleted", HttpStatus.OK);
    }
}

