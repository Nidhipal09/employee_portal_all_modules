package com.employeeportal.model.dto;

import com.employeeportal.model.PrimaryDetails;

public class OtherDetailsDTO {

    private Long otherId;
    private String illness;
    private String selfIntroduction;
    private PrimaryDetails primaryDetails; // Optional, if you want to include the whole object
    private boolean declarationAccepted;
    // Constructor


    public OtherDetailsDTO() {
    }

    public OtherDetailsDTO(Long otherId, String illness, String selfIntroduction, PrimaryDetails primaryDetails, boolean declarationAccepted) {
        this.otherId = otherId;
        this.illness = illness;
        this.selfIntroduction = selfIntroduction;
        this.primaryDetails = primaryDetails;
        this.declarationAccepted = declarationAccepted;
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
