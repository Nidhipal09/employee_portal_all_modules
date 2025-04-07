package com.employeeportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "visa_status")
public class VisaStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long visaId;

    private boolean citizen;
    private boolean expatOnGreenCard;
    private boolean expatOnWorkPermit;
    private boolean expatOnPermanentResidencyPermit;
    private boolean anyOtherStatus;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "primary_id", referencedColumnName = "primaryId")
    private PrimaryDetails primaryDetails;

    private String visaType;
    private String visaCategory;
    private String visaSubCategory;
    private String statusDescription;
    private String nationality;

    // Default constructor
    public VisaStatus() {
    }

    // Constructor to initialize all fields
    public VisaStatus(Long visaId, boolean citizen, boolean expatOnGreenCard, boolean expatOnWorkPermit, boolean expatOnPermanentResidencyPermit, boolean anyOtherStatus, PrimaryDetails primaryDetails, String visaType, String visaCategory, String visaSubCategory, String statusDescription, String nationality) {
        this.visaId = visaId;
        this.citizen = citizen;
        this.expatOnGreenCard = expatOnGreenCard;
        this.expatOnWorkPermit = expatOnWorkPermit;
        this.expatOnPermanentResidencyPermit = expatOnPermanentResidencyPermit;
        this.anyOtherStatus = anyOtherStatus;
        this.primaryDetails = primaryDetails;
        this.visaType = visaType;
        this.visaCategory = visaCategory;
        this.visaSubCategory = visaSubCategory;
        this.statusDescription = statusDescription;
        this.nationality = nationality;
    }

    // Getter and Setter methods
    public Long getVisaId() {
        return visaId;
    }

    public void setVisaId(Long visaId) {
        this.visaId = visaId;
    }

    public boolean isCitizen() {
        return citizen;
    }

    public void setCitizen(boolean citizen) {
        this.citizen = citizen;
    }

    public boolean isExpatOnGreenCard() {
        return expatOnGreenCard;
    }

    public void setExpatOnGreenCard(boolean expatOnGreenCard) {
        this.expatOnGreenCard = expatOnGreenCard;
    }

    public boolean isExpatOnWorkPermit() {
        return expatOnWorkPermit;
    }

    public void setExpatOnWorkPermit(boolean expatOnWorkPermit) {
        this.expatOnWorkPermit = expatOnWorkPermit;
    }

    public boolean isExpatOnPermanentResidencyPermit() {
        return expatOnPermanentResidencyPermit;
    }

    public void setExpatOnPermanentResidencyPermit(boolean expatOnPermanentResidencyPermit) {
        this.expatOnPermanentResidencyPermit = expatOnPermanentResidencyPermit;
    }

    public boolean isAnyOtherStatus() {
        return anyOtherStatus;
    }

    public void setAnyOtherStatus(boolean anyOtherStatus) {
        this.anyOtherStatus = anyOtherStatus;
    }

    public PrimaryDetails getPrimaryDetails() {
        return primaryDetails;
    }

    public void setPrimaryDetails(PrimaryDetails primaryDetails) {
        this.primaryDetails = primaryDetails;
    }

    public String getVisaType() {
        return visaType;
    }

    public void setVisaType(String visaType) {
        this.visaType = visaType;
    }

    public String getVisaCategory() {
        return visaCategory;
    }

    public void setVisaCategory(String visaCategory) {
        this.visaCategory = visaCategory;
    }

    public String getVisaSubCategory() {
        return visaSubCategory;
    }

    public void setVisaSubCategory(String visaSubCategory) {
        this.visaSubCategory = visaSubCategory;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
