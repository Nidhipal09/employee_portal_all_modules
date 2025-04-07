package com.employeeportal.model.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class VisaStatusDTO {
    private Long visaId;
    private boolean citizen;
    private boolean expatOnGreenCard;
    private boolean expatOnWorkPermit;
    private boolean expatOnPermanentResidencyPermit;
    private boolean anyOtherStatus;

    public VisaStatusDTO() {
    }

    public VisaStatusDTO(Long visaId, boolean citizen, boolean expatOnGreenCard, boolean expatOnWorkPermit, boolean expatOnPermanentResidencyPermit, boolean anyOtherStatus) {
        this.visaId = visaId;
        this.citizen = citizen;
        this.expatOnGreenCard = expatOnGreenCard;
        this.expatOnWorkPermit = expatOnWorkPermit;
        this.expatOnPermanentResidencyPermit = expatOnPermanentResidencyPermit;
        this.anyOtherStatus = anyOtherStatus;
    }

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

}
