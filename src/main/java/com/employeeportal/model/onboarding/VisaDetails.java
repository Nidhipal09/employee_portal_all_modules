package com.employeeportal.model.onboarding;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "visa_details")
public class VisaDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long visaDetailsId;
    private String workPermitDetails;
    private Date workPermitValidTill;
    private String passportCopy;
    private String passportCopyUrl;
    private String status; 
    private String country; 

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "employeeId", nullable = false) 
    private Employee employee; 
    
}

