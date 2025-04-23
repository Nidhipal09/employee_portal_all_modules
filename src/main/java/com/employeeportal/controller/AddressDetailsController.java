package com.employeeportal.controller;

import com.employeeportal.exception.ResourceNotFoundException;
import com.employeeportal.model.AddressDetails;
import com.employeeportal.model.AddressResponseDTO;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.dto.AddressDTO;
import com.employeeportal.service.AddressDetailsService;
import com.employeeportal.service.PrimaryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressDetails")
@CrossOrigin(origins = "*")
public class AddressDetailsController {

    @Autowired
    private AddressDetailsService addressDetailsService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<AddressResponseDTO> saveAddressDetails(@RequestBody AddressDTO addressDetails) {
        AddressResponseDTO addaddressDetails = addressDetailsService.saveAddressDetails(addressDetails);
        return new ResponseEntity<>(addaddressDetails, HttpStatus.CREATED);

    }
    @GetMapping("/allAddressDetails")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE')")
    public ResponseEntity<List<AddressDetails>> getAllAddressDetails() {
        List<AddressDetails> allAddressDetails = addressDetailsService.getAllAddressDetails();
        return new ResponseEntity<>(allAddressDetails, HttpStatus.OK);
    }
    @GetMapping("/address/{addressId}")
    public ResponseEntity<AddressDetails> getAddressDetailsById(@PathVariable("addressId") Long  addressId) {
        AddressDetails addresDetailsById = addressDetailsService.getAddressDetailsById( addressId);

        if (addresDetailsById == null) {
            throw new ResourceNotFoundException("User not found with id " +  addressId);
        }
        return new ResponseEntity<>(addresDetailsById, HttpStatus.OK);
    }
    @PutMapping("/update/{addressId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<AddressDetails> updateAddressDetailsById(@PathVariable("addressId") Long addressId, @RequestBody AddressDetails addressDetails) {
        AddressDetails updateAddressDetails = addressDetailsService.updateAddressDetailsById(addressId, addressDetails);
        // return ResponseEntity.ok(updateUser);
        return new ResponseEntity<>(updateAddressDetails, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity<String> deleteAddressDetailsById(@PathVariable("addressId") Long addressId) {
        AddressDetails isDeleted = addressDetailsService.deleteAddressDetailsById(addressId);
        return new ResponseEntity<>("data deleted", HttpStatus.OK);
    }
}
