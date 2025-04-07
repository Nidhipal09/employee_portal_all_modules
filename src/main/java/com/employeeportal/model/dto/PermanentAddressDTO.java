package com.employeeportal.model.dto;

import java.time.LocalDate;

public class PermanentAddressDTO {

    private Long permanentId;
    private String houseNumber;
    private String streetName;
    private String town;
    private String pincode;
    private String state;
    private String city;
    private LocalDate stayFrom;
    private LocalDate stayTo;
    private String emergencyContactNumber;
    private String emergencyContactNameAndRelationship;

    // Default constructor
    public PermanentAddressDTO() {
    }

    // Constructor
    public PermanentAddressDTO(Long permanentId, String houseNumber, String streetName, String town,
                               String pincode, String state, String city, LocalDate stayFrom,
                               LocalDate stayTo, String emergencyContactNumber,
                               String emergencyContactNameAndRelationship) {
        this.permanentId = permanentId;
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.town = town;
        this.pincode = pincode;
        this.state = state;
        this.city = city;
        this.stayFrom = stayFrom;
        this.stayTo = stayTo;
        this.emergencyContactNumber = emergencyContactNumber;
        this.emergencyContactNameAndRelationship = emergencyContactNameAndRelationship;
    }

    // Getters and Setters
    public Long getPermanentId() {
        return permanentId;
    }

    public void setPermanentId(Long permanentId) {
        this.permanentId = permanentId;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getStayFrom() {
        return stayFrom;
    }

    public void setStayFrom(LocalDate stayFrom) {
        this.stayFrom = stayFrom;
    }

    public LocalDate getStayTo() {
        return stayTo;
    }

    public void setStayTo(LocalDate stayTo) {
        this.stayTo = stayTo;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getEmergencyContactNameAndRelationship() {
        return emergencyContactNameAndRelationship;
    }

    public void setEmergencyContactNameAndRelationship(String emergencyContactNameAndRelationship) {
        this.emergencyContactNameAndRelationship = emergencyContactNameAndRelationship;
    }

    @Override
    public String toString() {
        return "PermanentAddressDTO{" +
                "permanentId=" + permanentId +
                ", houseNumber='" + houseNumber + '\'' +
                ", streetName='" + streetName + '\'' +
                ", town='" + town + '\'' +
                ", pincode='" + pincode + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", stayFrom=" + stayFrom +
                ", stayTo=" + stayTo +
                ", emergencyContactNumber='" + emergencyContactNumber + '\'' +
                ", emergencyContactNameAndRelationship='" + emergencyContactNameAndRelationship + '\'' +
                '}';
    }
}
