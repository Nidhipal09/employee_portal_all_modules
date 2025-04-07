package com.employeeportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="other_details")
public class OtherDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long otherId;
    private String illness;
    private String selfIntroduction;
    private boolean declarationAccepted;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "primary_id", nullable = false)
    private PrimaryDetails primaryDetails;

    public OtherDetails() {
    }

    public OtherDetails(Long otherId, String illness, String selfIntroduction, boolean declarationAccepted, PrimaryDetails primaryDetails) {
        this.otherId = otherId;
        this.illness = illness;
        this.selfIntroduction = selfIntroduction;
        this.declarationAccepted = declarationAccepted;
        this.primaryDetails = primaryDetails;
    }

    public Long getOtherId() {
        return otherId;
    }

    public void setOtherId(Long otherId) {
        this.otherId = otherId;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public PrimaryDetails getPrimaryDetails() {
        return primaryDetails;
    }

    public void setPrimaryDetails(PrimaryDetails primaryDetails) {
        this.primaryDetails = primaryDetails;
    }

    public boolean isDeclarationAccepted() {
        return declarationAccepted;
    }

    public void setDeclarationAccepted(boolean declarationAccepted) {
        this.declarationAccepted = declarationAccepted;
    }
}


