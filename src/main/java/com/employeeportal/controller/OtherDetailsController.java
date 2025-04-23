package com.employeeportal.controller;

import com.employeeportal.exception.ResourceNotFoundException;
import com.employeeportal.model.OtherDetails;
import com.employeeportal.model.PassportDetails;
import com.employeeportal.model.dto.OtherDetailsDTO;
import com.employeeportal.repository.PrimaryDetailsRepository;
import com.employeeportal.service.OtherDetailsService;
import com.employeeportal.service.PassportDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/otherDetails")
@CrossOrigin(origins = "*")
public class OtherDetailsController {
    @Autowired
    private OtherDetailsService otherDetailsService ;

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<OtherDetails> saveOtherDetails(@RequestBody OtherDetailsDTO otherDetails) {
        OtherDetails addOtherDetails = otherDetailsService.saveOtherDetails(otherDetails);
        return new ResponseEntity<>(addOtherDetails, HttpStatus.CREATED);

    }
    @GetMapping("/allOtherDetails")

    public ResponseEntity<List<OtherDetails>> getAllOtherDetails() {
        List<OtherDetails> allOtherDetails =otherDetailsService.getAllOtherDetails();
        return new ResponseEntity<>(allOtherDetails, HttpStatus.OK);
    }
    @GetMapping("/other/{otherId}")
    public ResponseEntity<OtherDetails> getOtherDetailsById(@PathVariable("otherId") Long  otherId) {
        OtherDetails OtherDetailsById = otherDetailsService.getOtherDetailsById(otherId);

        if (OtherDetailsById == null) {
            throw new ResourceNotFoundException("User not found with id " + otherId);
        }
        return new ResponseEntity<>(OtherDetailsById, HttpStatus.OK);
    }
    @PutMapping("/update/{otherId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<OtherDetails> updateOtherDetailsById(@PathVariable("otherId") Long otherId, @RequestBody OtherDetails  otherDetails) {
        OtherDetails updateOtherDetails = otherDetailsService. updateOtherDetailsById(otherId, otherDetails);

        return new ResponseEntity<>(updateOtherDetails, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{otherId}")
    public ResponseEntity<String> deleteOtherDetailsById(@PathVariable("otherId") Long otherId) {
        OtherDetails isDeleted = otherDetailsService.deleteOtherDetailsById(otherId);
        return new ResponseEntity<>("data deleted", HttpStatus.OK);
    }

}
