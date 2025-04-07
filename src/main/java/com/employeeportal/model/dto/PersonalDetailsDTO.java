package com.employeeportal.model.dto;
import java.time.LocalDate;

public class PersonalDetailsDTO {

    private Long personalId;
    private String firstName;
    private String middleName;
    private String surname;
    private String imageUrl;
    private String dob;
    private String gender;
    private String motherName;
    private String fatherName;
    private String email;
    private String mobile;
    private String aadharNumber;
    private String aadharUrl;
    private String panNumber;
    private String panUrl;
    private String passportNumber;
    private String passportUrl;

    // Default constructor
    public PersonalDetailsDTO() {
    }

    public PersonalDetailsDTO(Long personalId, String firstName, String middleName, String surname, String imageUrl, String dob, String gender, String motherName, String fatherName, String email, String mobile, String aadharNumber, String aadharUrl, String panNumber, String panUrl, String passportNumber, String passportUrl) {
        this.personalId = personalId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = surname;
        this.imageUrl = imageUrl;
        this.dob = dob;
        this.gender = gender;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.email = email;
        this.mobile = mobile;
        this.aadharNumber = aadharNumber;
        this.aadharUrl = aadharUrl;
        this.panNumber = panNumber;
        this.panUrl = panUrl;
        this.passportNumber = passportNumber;
        this.passportUrl = passportUrl;
    }

    // Getters and Setters
    public Long getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Long personalId) {
        this.personalId = personalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getAadharUrl() {
        return aadharUrl;
    }

    public void setAadharUrl(String aadharUrl) {
        this.aadharUrl = aadharUrl;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getPanUrl() {
        return panUrl;
    }

    public void setPanUrl(String panUrl) {
        this.panUrl = panUrl;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportUrl() {
        return passportUrl;
    }

    public void setPassportUrl(String passportUrl) {
        this.passportUrl = passportUrl;
    }

    @Override
    public String toString() {
        return "PersonalDetailsDTO{" +
                "personalId=" + personalId +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", surname='" + surname + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", motherName='" + motherName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", aadharNumber='" + aadharNumber + '\'' +
                ", aadharUrl='" + aadharUrl + '\'' +
                ", panNumber='" + panNumber + '\'' +
                ", panUrl='" + panUrl + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", passportUrl='" + passportUrl + '\'' +
                '}';
    }
}
