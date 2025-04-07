package com.employeeportal.model.dto;
public class PassportDetailsDTO {
    private Long passportId;
    private String passportNumber;
    private String issueDate;
    private String placeOfIssue;
    private String expiryDate;
    private String countryOfIssue;
    private String nationality;

    // Default constructor
    public PassportDetailsDTO() {
    }

    public PassportDetailsDTO(Long passportId, String passportNumber, String issueDate, String placeOfIssue, String expiryDate, String countryOfIssue, String nationality) {
        this.passportId = passportId;
        this.passportNumber = passportNumber;
        this.issueDate = issueDate;
        this.placeOfIssue = placeOfIssue;
        this.expiryDate = expiryDate;
        this.countryOfIssue = countryOfIssue;
        this.nationality = nationality;
    }

    public Long getPassportId() {
        return passportId;
    }

    public void setPassportId(Long passportId) {
        this.passportId = passportId;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getPlaceOfIssue() {
        return placeOfIssue;
    }

    public void setPlaceOfIssue(String placeOfIssue) {
        this.placeOfIssue = placeOfIssue;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCountryOfIssue() {
        return countryOfIssue;
    }

    public void setCountryOfIssue(String countryOfIssue) {
        this.countryOfIssue = countryOfIssue;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
