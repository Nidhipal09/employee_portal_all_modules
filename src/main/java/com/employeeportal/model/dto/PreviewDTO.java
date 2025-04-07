package com.employeeportal.model.dto;

public class PreviewDTO {

    private Long previewId;
    private String employeeSignatureUrl;
    private String signatureDate;
    private String place;

    // Default constructor
    public PreviewDTO() {
    }

    // Parameterized constructor
    public PreviewDTO(Long previewId, String employeeSignatureUrl, String signatureDate, String place) {
        this.previewId = previewId;
        this.employeeSignatureUrl = employeeSignatureUrl;
        this.signatureDate = signatureDate;
        this.place = place;
    }

    // Getters and Setters
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

    @Override
    public String toString() {
        return "PreviewDTO{" +
                "previewId=" + previewId +
                ", employeeSignatureUrl='" + employeeSignatureUrl + '\'' +
                ", signatureDate='" + signatureDate + '\'' +
                ", place='" + place + '\'' +
                '}';
    }
}
