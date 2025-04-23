package com.employeeportal.service;

import com.employeeportal.model.DocumentCertificates;

import java.util.List;

public interface DocumentCertificatesService {
    DocumentCertificates saveDocument(DocumentCertificates document);
    DocumentCertificates getDocumentById(Long documentId);
    List<DocumentCertificates> getAllDocuments();
    DocumentCertificates updateDocument(Long documentId, DocumentCertificates updatedDocument);
    void deleteDocument(Long documentId);
}
