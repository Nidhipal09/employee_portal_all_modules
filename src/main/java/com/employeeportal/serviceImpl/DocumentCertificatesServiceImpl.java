package com.employeeportal.serviceImpl;

import com.employeeportal.model.DocumentCertificates;
import com.employeeportal.repository.DocumentCertificatesRepository;
import com.employeeportal.service.DocumentCertificatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentCertificatesServiceImpl implements DocumentCertificatesService {

    @Autowired
    private DocumentCertificatesRepository documentRepository;

    @Override
    public DocumentCertificates saveDocument(DocumentCertificates document) {
        return documentRepository.save(document);
    }

    @Override
    public DocumentCertificates getDocumentById(Long documentId) {
        return documentRepository.findById(documentId)
                .orElseThrow(() -> new EntityNotFoundException("Document not found with ID: " + documentId));
    }

    @Override
    public List<DocumentCertificates> getAllDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public DocumentCertificates updateDocument(Long documentId, DocumentCertificates updatedDocument) {
        Optional<DocumentCertificates> optionalDocument = documentRepository.findById(documentId);
        if (!optionalDocument.isPresent()) {
            throw new EntityNotFoundException("Document not found with ID: " + documentId);
        }

        DocumentCertificates existingDocument = optionalDocument.get();

        // Update only if values are provided
        existingDocument.setDegreeCertificate(updatedDocument.getDegreeCertificate() != null ?
                updatedDocument.getDegreeCertificate() : existingDocument.getDegreeCertificate());
        existingDocument.setDegreeCertificateUrl(isValidValue(updatedDocument.getDegreeCertificateUrl()) ?
                updatedDocument.getDegreeCertificateUrl() : existingDocument.getDegreeCertificateUrl());
        existingDocument.setDocumentType(updatedDocument.getDocumentType() != null ?
                updatedDocument.getDocumentType() : existingDocument.getDocumentType());
        existingDocument.setPassingCertificate(updatedDocument.getPassingCertificate() != null ?
                updatedDocument.getPassingCertificate() : existingDocument.getPassingCertificate());
        existingDocument.setPassingCertificateUrl(isValidValue(updatedDocument.getPassingCertificateUrl()) ?
                updatedDocument.getPassingCertificateUrl() : existingDocument.getPassingCertificateUrl());

        return documentRepository.save(existingDocument);
    }

    @Override
    public void deleteDocument(Long documentId) {
        if (!documentRepository.existsById(documentId)) {
            throw new EntityNotFoundException("Document not found with ID: " + documentId);
        }
        documentRepository.deleteById(documentId);
    }

    // Utility method
    private boolean isValidValue(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
