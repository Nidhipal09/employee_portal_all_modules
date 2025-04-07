package com.employeeportal.model.dto;


import com.employeeportal.model.DocumentsType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class DocumentDTO {
    private Long documentId;
    private Long userId;
    @Enumerated(EnumType.STRING)
    private DocumentsType documentType;
    private boolean degreeCertificate;
    private String degreeCertificateUrl;
    private boolean passingCertificate;
    private String passingCertificateUrl;

    public DocumentDTO() {
    }

    public DocumentDTO(Long documentId, Long userId, DocumentsType documentType, boolean degreeCertificate, String degreeCertificateUrl, boolean passingCertificate, String passingCertificateUrl) {
        this.documentId = documentId;
        this.userId = userId;
        this.documentType = documentType;
        this.degreeCertificate = degreeCertificate;
        this.degreeCertificateUrl = degreeCertificateUrl;
        this.passingCertificate = passingCertificate;
        this.passingCertificateUrl = passingCertificateUrl;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public DocumentsType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentsType documentType) {
        this.documentType = documentType;
    }

    public boolean isDegreeCertificate() {
        return degreeCertificate;
    }

    public void setDegreeCertificate(boolean degreeCertificate) {
        this.degreeCertificate = degreeCertificate;
    }

    public String getDegreeCertificateUrl() {
        return degreeCertificateUrl;
    }

    public void setDegreeCertificateUrl(String degreeCertificateUrl) {
        this.degreeCertificateUrl = degreeCertificateUrl;
    }

    public boolean isPassingCertificate() {
        return passingCertificate;
    }

    public void setPassingCertificate(boolean passingCertificate) {
        this.passingCertificate = passingCertificate;
    }

    public String getPassingCertificateUrl() {
        return passingCertificateUrl;
    }

    public void setPassingCertificateUrl(String passingCertificateUrl) {
        this.passingCertificateUrl = passingCertificateUrl;
    }
}
