package com.employeeportal.controller;

import com.employeeportal.exception.ResourceNotFoundException;
import com.employeeportal.model.ProfessionalReferences;
import com.employeeportal.model.dto.ProfessionalRequestDTO;
import com.employeeportal.model.dto.ProfessionalResponseDTO;
import com.employeeportal.service.ProfessionalReferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professional")
@CrossOrigin(origins = "*")
public class ProfessionalReferencesController {
    @Autowired
    private ProfessionalReferencesService professionalReferencesService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<ProfessionalResponseDTO> saveProfessionalReferences(@RequestBody ProfessionalRequestDTO professionalReferences) {
        ProfessionalResponseDTO addProfessionalRefer = professionalReferencesService.saveProfessionalReferences(professionalReferences);
        return new ResponseEntity<>(addProfessionalRefer, HttpStatus.CREATED);
    }

    @GetMapping("/profession{professionalId}")
    public ResponseEntity<ProfessionalReferences> getProfessionalReferencesById(@PathVariable("professionalId") Long professionalId) {
        ProfessionalReferences ProfessionalReferencesById = professionalReferencesService.getProfessionalReferencesById(professionalId);
        if (ProfessionalReferencesById == null) {
            throw new ResourceNotFoundException("User not found with id " + professionalId);
        }
        return new ResponseEntity<>(ProfessionalReferencesById, HttpStatus.OK);
    }

    // Update Professional References by ID
    @PutMapping("/update/{professionalId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<ProfessionalReferences> updateProfessionalReferences(@PathVariable("professionalId") Long professionalId,
                                                                               @RequestBody ProfessionalReferences professionalReferences) {
        ProfessionalReferences updatedProfessionalReferences = professionalReferencesService.updateProfessionalReferencesById(professionalId, professionalReferences);
        if (updatedProfessionalReferences == null) {
            throw new ResourceNotFoundException("Professional reference not found with id " + professionalId);
        }
        return new ResponseEntity<>(updatedProfessionalReferences, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{professionalId}")
    public ResponseEntity<String> deleteProfessionalReferences(@PathVariable("professionalId") Long professionalId) {
        ProfessionalReferences deletedProfessionalReferences = professionalReferencesService.deleteProfessionalReferencesById(professionalId);
        if (deletedProfessionalReferences == null) {
            throw new ResourceNotFoundException("Professional reference not found with id " + professionalId);
        }
        return new ResponseEntity<>("Professional reference deleted successfully with id: " + professionalId, HttpStatus.OK);
    }
}







