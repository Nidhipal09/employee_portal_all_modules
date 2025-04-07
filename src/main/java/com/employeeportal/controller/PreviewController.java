package com.employeeportal.controller;

import com.employeeportal.exception.ResourceNotFoundException;
import com.employeeportal.model.PermanentAddress;
import com.employeeportal.model.PersonalDetails;
import com.employeeportal.model.Preview;
import com.employeeportal.service.PersonalDetailsService;
import com.employeeportal.service.PreviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preview")
@CrossOrigin(origins = "*")
public class PreviewController {
    @Autowired
    private PreviewService previewService;

    @PostMapping("/save")
    public ResponseEntity<Preview> savePreview(@RequestBody Preview  preview) {
        Preview addPreview = previewService.savePreview(preview);
        return new ResponseEntity<>(addPreview, HttpStatus.CREATED);

    }
    @GetMapping("/allPreview")

    public ResponseEntity<List<Preview>> getAllPreview() {
        List<Preview> allPreview = previewService.getAllPreview();
        return new ResponseEntity<>(allPreview, HttpStatus.OK);
    }
    @GetMapping("/preview/{previewId}")
    public ResponseEntity<Preview> getPreviewById(@PathVariable("previewId") Long  previewId) {
        Preview PreviewById = previewService.getPreviewById(previewId);

        if (PreviewById == null) {
            throw new ResourceNotFoundException("User not found with id " + previewId);
        }
        return new ResponseEntity<>(PreviewById, HttpStatus.OK);
    }
    @PutMapping("/update/{previewId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<Preview> updatePreviewById(@PathVariable("previewId") Long previewId, @RequestBody Preview preview) {
        Preview updatePreview = previewService. updatePreviewById(previewId, preview);

        return new ResponseEntity<>(updatePreview, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{previewId}")
    public ResponseEntity<String> deletePreviewById(@PathVariable("personalId") Long previewId) {
        Preview isDeleted = previewService.deletePreviewById(previewId);
        return new ResponseEntity<>("data deleted", HttpStatus.OK);
    }

}
