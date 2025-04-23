package com.employeeportal.controller;

import com.employeeportal.exception.ResourceNotFoundException;
import com.employeeportal.model.*;
import com.employeeportal.model.AddressResponseDTO;
import com.employeeportal.model.dto.*;
import com.employeeportal.service.*;
import com.employeeportal.util.JwtUtil;
import com.employeeportal.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/primary")
@CrossOrigin(origins = "*")
public class PrimaryController {
    private final ResponseUtil responseUtil;
    private final JwtUtil jwtUtil;
    private final PrimaryDetailsService primaryDetailsService;
    private final PersonalDetailsService personalDetailsService;
    private final AddressDetailsService addressDetailsService;
    private final EducationalQualificationService educationalQualificationService;
    private final ProfessionalReferencesService professionalReferencesService;
    private final OtherDetailsService otherDetailsService;


    public PrimaryController(ResponseUtil responseUtil, JwtUtil jwtUtil, PrimaryDetailsService primaryDetailsService, PersonalDetailsService personalDetailsService, AddressDetailsService addressDetailsService, EducationalQualificationService educationalQualificationService, ProfessionalReferencesService professionalReferencesService, OtherDetailsService otherDetailsService) {
        this.responseUtil = responseUtil;
        this.jwtUtil = jwtUtil;
        this.primaryDetailsService = primaryDetailsService;
        this.personalDetailsService = personalDetailsService;
        this.addressDetailsService = addressDetailsService;
        this.educationalQualificationService = educationalQualificationService;
        this.professionalReferencesService = professionalReferencesService;
        this.otherDetailsService = otherDetailsService;
    }
    @PostMapping("/createPersonal")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<PersonalDetails> savePersonalDetails(@RequestBody PersonalDetailsDTO personalDetails) {
        PersonalDetails addPersonalDetails = personalDetailsService.savePersonalDetails(personalDetails);
        return new ResponseEntity<>(addPersonalDetails, HttpStatus.CREATED);

    }
    @PostMapping("/createAddress")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<AddressResponseDTO> saveAddressDetails(@RequestBody AddressDTO addressDetails) {
        AddressResponseDTO addaddressDetails = addressDetailsService.saveAddressDetails(addressDetails);
        return new ResponseEntity<>(addaddressDetails, HttpStatus.CREATED);

    }
    @PostMapping("/createEducational")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<EducationResponseDTO> saveEducationalQualification(@RequestBody EducationDTO educationalQualification) {
        EducationResponseDTO addeducationalQualification = educationalQualificationService.saveEducationalQualification(educationalQualification);
        return new ResponseEntity<>(addeducationalQualification, HttpStatus.CREATED);

    }
    @PostMapping("/createProfessional")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<ProfessionalResponseDTO> saveProfessionalReferences(@RequestBody ProfessionalRequestDTO professionalReferences) {
        ProfessionalResponseDTO addProfessionalRefer = professionalReferencesService.saveProfessionalReferences(professionalReferences);
        return new ResponseEntity<>(addProfessionalRefer, HttpStatus.CREATED);
    }

    @PostMapping("/createOthers")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<OtherDetails> saveOtherDetails(@RequestBody OtherDetailsDTO otherDetails) {
        OtherDetails addOtherDetails = otherDetailsService.saveOtherDetails(otherDetails);
        return new ResponseEntity<>(addOtherDetails, HttpStatus.CREATED);

    }
    @GetMapping("/preview/{primaryId}")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<PrimaryPreviewResponse> getPrimaryDetailsById(@PathVariable("primaryId") Long  primaryId) {
        PrimaryPreviewResponse primaryDetailsById = primaryDetailsService.getPrimaryDetails( primaryId);

        if (primaryDetailsById == null) {
            throw new ResourceNotFoundException("User not found with id " +  primaryId);
        }
        return new ResponseEntity<>(primaryDetailsById, HttpStatus.OK);
    }
    @PutMapping("/updatePersonal")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<PersonalDetails> updatePersonalDetails(@RequestBody PersonalDetailsDTO personalDetails) {
        PersonalDetails addPersonalDetails = personalDetailsService.updatePersonalDetails(personalDetails);
        return new ResponseEntity<>(addPersonalDetails, HttpStatus.CREATED);

    }
    @PutMapping("/updateAddress")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<AddressResponseDTO> updateAddressDetails(@RequestBody AddressDTO addressDetails) {
        AddressResponseDTO addaddressDetails = addressDetailsService.updateAddressDetails(addressDetails);
        return new ResponseEntity<>(addaddressDetails, HttpStatus.CREATED);

    }
    @PutMapping("/updateEducational")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<EducationResponseDTO> updateEducationalQualification(@RequestBody EducationDTO educationalQualification) {
        EducationResponseDTO addeducationalQualification = educationalQualificationService.updateEducationalQualification(educationalQualification);
        return new ResponseEntity<>(addeducationalQualification, HttpStatus.CREATED);

    }
    @PutMapping("/updateOthers")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<OtherDetails> updateOtherDetails(@RequestBody OtherDetailsDTO otherDetails) {
        OtherDetails addOtherDetails = otherDetailsService.updateOtherDetails(otherDetails);
        return new ResponseEntity<>(addOtherDetails, HttpStatus.CREATED);

    }
    @PutMapping("/updateProfessional")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<ProfessionalResponseDTO> updateProfessionalReferences(@RequestBody ProfessionalRequestDTO professionalReferences) {
        ProfessionalResponseDTO addProfessionalRefer = professionalReferencesService.updateProfessionalReferences(professionalReferences);
        return new ResponseEntity<>(addProfessionalRefer, HttpStatus.CREATED);
    }
    @PostMapping("/sendPreviewDetailsToHR")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<?> sendPreviewDetailsToHR(@RequestBody PreviewDetailsDTO previewDetailsDTO) {
        String addOtherDetails = primaryDetailsService.sendPreviewDetailsToHR(previewDetailsDTO);
        return new ResponseEntity<>(addOtherDetails, HttpStatus.OK);
    }

}
