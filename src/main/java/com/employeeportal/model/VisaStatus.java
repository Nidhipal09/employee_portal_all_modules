package com.employeeportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "visa_status")
public class VisaStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long visaId;
    private Boolean citizen;
    private Boolean expatOnGreenCard;
    private Boolean expatOnWorkPermit;
    private Boolean expatOnPermanentResidencyPermit;
    private Boolean anyOtherStatus;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "primary_id", referencedColumnName = "primaryId")
    private PrimaryDetails primaryDetails;


}

