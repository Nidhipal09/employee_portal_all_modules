package com.employeeportal.model.onboarding;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "passport_details")
public class PassportDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int passportId;
    private String passportNumber;
    private Date dateOfIssue;
    private String placeOfIssue;
    private String countryOfIssue;
    private Date validUpto;
    private String nationality;
    private String passportUrl;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "employeeId", nullable = false) 
    private Employee employee; 

}
