package com.employeeportal.model.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
public class VisaStatusDTO {
    private Long visaId;
    private Boolean citizen;
    private Boolean expatOnGreenCard;
    private Boolean expatOnWorkPermit;
    private Boolean expatOnPermanentResidencyPermit;
    private Boolean anyOtherStatus;



}
