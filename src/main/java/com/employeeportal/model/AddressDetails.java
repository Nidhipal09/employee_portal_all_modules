package com.employeeportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name="address_details")
public class AddressDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String stayFrom;
    private String stayTo;
    private String addressLine;
    private String pincode;
    private String country;
    private String contactNumberWithRelationship;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "primary_id", nullable = false)
    private PrimaryDetails primaryDetails;

    public AddressDetails() {
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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

    public PrimaryDetails getPrimaryDetails() {
        return primaryDetails;
    }

    public void setPrimaryDetails(PrimaryDetails primaryDetails) {
        this.primaryDetails = primaryDetails;
    }

    public AddressDetails(Long addressId, String stayFrom, String stayTo, String addressLine, String pincode, String country, String contactNumberWithRelationship, PrimaryDetails primaryDetails) {
        this.addressId = addressId;
        this.stayFrom = stayFrom;
        this.stayTo = stayTo;
        this.addressLine = addressLine;
        this.pincode = pincode;
        this.country = country;
        this.contactNumberWithRelationship = contactNumberWithRelationship;
        this.primaryDetails = primaryDetails;
    }
}
