package com.employeeportal.controller;

import com.employeeportal.exception.ResourceNotFoundException;
import com.employeeportal.model.DocumentCertificates;
import com.employeeportal.service.DocumentCertificatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documentCertificates")
@CrossOrigin(origins = "*")
public class DocumentCertificatesController {

    @Autowired
    private DocumentCertificatesService documentService;

    // Save new document certificate
    @PostMapping("/save")
    public ResponseEntity<DocumentCertificates> saveDocument(@RequestBody DocumentCertificates document) {
        DocumentCertificates savedDocument = documentService.saveDocument(document);
        return new ResponseEntity<>(savedDocument, HttpStatus.CREATED);
    }

    // Get document by ID
    @GetMapping("/get/{documentId}")
    public ResponseEntity<DocumentCertificates> getDocumentById(@PathVariable("documentId") Long documentId) {
        DocumentCertificates document = documentService.getDocumentById(documentId);
        if (document == null) {
            throw new ResourceNotFoundException("Document not found with ID " + documentId);
        }
        return new ResponseEntity<>(document, HttpStatus.OK);
    }

    // Get all documents
    @GetMapping("/all")
    public ResponseEntity<List<DocumentCertificates>> getAllDocuments() {
        List<DocumentCertificates> documents = documentService.getAllDocuments();
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    // Update document by ID
    @PutMapping("/update/{documentId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<DocumentCertificates> updateDocument(@PathVariable("documentId") Long documentId,
                                                               @RequestBody DocumentCertificates document) {
        DocumentCertificates updatedDocument = documentService.updateDocument(documentId, document);
        return new ResponseEntity<>(updatedDocument, HttpStatus.OK);
    }

    // Delete document by ID
    @DeleteMapping("/delete/{documentId}")
    public ResponseEntity<String> deleteDocument(@PathVariable("documentId") Long documentId) {
        documentService.deleteDocument(documentId);
        return new ResponseEntity<>("Document deleted successfully with ID: " + documentId, HttpStatus.OK);
    }
}
