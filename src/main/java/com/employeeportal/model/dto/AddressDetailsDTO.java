package com.employeeportal.model.dto;

public class AddressDetailsDTO {
    private String stayFrom;
    private String stayTo;
    private String addressLine;
    private String pincode;
    private String country;
    private String contactNumberWithRelationship;

    public AddressDetailsDTO() {
    }

    public AddressDetailsDTO(String stayFrom, String stayTo, String addressLine, String pincode, String country, String contactNumberWithRelationship) {
        this.stayFrom = stayFrom;
        this.stayTo = stayTo;
        this.addressLine = addressLine;
        this.pincode = pincode;
        this.country = country;
        this.contactNumberWithRelationship = contactNumberWithRelationship;
    }

    public String getStayFrom() {
        return stayFrom;
    }

    public void setStayFrom(String stayFrom) {
        this.stayFrom = stayFrom;
    }

    public String getStayTo() {
        return stayTo;
    }

    public void setStayTo(String stayTo) {
        this.stayTo = stayTo;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContactNumberWithRelationship() {
        return contactNumberWithRelationship;
    }

    public void setContactNumberWithRelationship(String contactNumberWithRelationship) {
        this.contactNumberWithRelationship = contactNumberWithRelationship;
    }
}
