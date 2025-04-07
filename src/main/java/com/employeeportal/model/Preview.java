package com.employeeportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="preview")
public class Preview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long previewId;
    private String employeeSignatureUrl;
    private String signatureDate;
    private String place;
    @OneToOne
    @JsonIgnore// Assuming one-to-one relationship with PrimaryDetails
    @JoinColumn(name = "primary_id") // Foreign key column in the Preview table
    private PrimaryDetails primaryDetails;
    public Preview() {
    }

    public Preview(Long previewId, String employeeSignatureUrl, String signatureDate, String place) {
        this.previewId = previewId;
        this.employeeSignatureUrl = employeeSignatureUrl;
        this.signatureDate = signatureDate;
        this.place = place;
    }

    public Long getPreviewId() {
        return previewId;
    }

    public void setPreviewId(Long previewId) {
        this.previewId = previewId;
    }

    public String getEmployeeSignatureUrl() {
        return employeeSignatureUrl;
    }

    public void setEmployeeSignatureUrl(String employeeSignatureUrl) {
        this.employeeSignatureUrl = employeeSignatureUrl;
    }

    public String getSignatureDate() {
        return signatureDate;
    }

    public void setSignatureDate(String signatureDate) {
        this.signatureDate = signatureDate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public PrimaryDetails getPrimaryDetails() {
        return primaryDetails;
    }

    public void setPrimaryDetails(PrimaryDetails primaryDetails) {
        this.primaryDetails = primaryDetails;
    }
}
