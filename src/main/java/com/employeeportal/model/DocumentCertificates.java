package com.employeeportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="document_certificates")
public class DocumentCertificates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;
    private Boolean degreeCertificate;
    private String degreeCertificateUrl;
    @Enumerated(EnumType.STRING)
    private DocumentsType documentType;
    private Boolean passingCertificate;
    private String passingCertificateUrl;
    @OneToOne
    @JsonIgnore// One PermanentAddress relates to one PrimaryDetails
    @JoinColumn(name = "primary_id", nullable = false)
    // Foreign key column in document_certificates table
    private PrimaryDetails primaryDetails;

    public DocumentCertificates() {
    }

    public DocumentCertificates(Long documentId, Boolean degreeCertificate, String degreeCertificateUrl, DocumentsType documentType, Boolean passingCertificate, String passingCertificateUrl, PrimaryDetails primaryDetails) {
        this.documentId = documentId;
        this.degreeCertificate = degreeCertificate;
        this.degreeCertificateUrl = degreeCertificateUrl;
        this.documentType = documentType;
        this.passingCertificate = passingCertificate;
        this.passingCertificateUrl = passingCertificateUrl;
        this.primaryDetails = primaryDetails;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Boolean getDegreeCertificate() {
        return degreeCertificate;
    }

    public void setDegreeCertificate(Boolean degreeCertificate) {
        this.degreeCertificate = degreeCertificate;
    }

    public String getDegreeCertificateUrl() {
        return degreeCertificateUrl;
    }

    public void setDegreeCertificateUrl(String degreeCertificateUrl) {
        this.degreeCertificateUrl = degreeCertificateUrl;
    }

    public DocumentsType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentsType documentType) {
        this.documentType = documentType;
    }

    public Boolean getPassingCertificate() {
        return passingCertificate;
    }

    public void setPassingCertificate(Boolean passingCertificate) {
        this.passingCertificate = passingCertificate;
    }

    public String getPassingCertificateUrl() {
        return passingCertificateUrl;
    }

    public void setPassingCertificateUrl(String passingCertificateUrl) {
        this.passingCertificateUrl = passingCertificateUrl;
    }

    public PrimaryDetails getPrimaryDetails() {
        return primaryDetails;
    }

    public void setPrimaryDetails(PrimaryDetails primaryDetails) {
        this.primaryDetails = primaryDetails;
    }
}
