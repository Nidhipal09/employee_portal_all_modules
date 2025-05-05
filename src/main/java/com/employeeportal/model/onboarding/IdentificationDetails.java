package com.employeeportal.model.onboarding;

import javax.persistence.*;

import com.employeeportal.model.registration.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "identification_details")
public class IdentificationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int identityId;

    @Enumerated(EnumType.STRING)
    @Column(name = "identity_type", nullable = false, length = 50)
    private IdentityType identityType;

    @Column(name = "identification_number", nullable = false, length = 100)
    private String identificationNumber;

    @Column(name = "identification_url", length = 255)
    private String identificationUrl;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;
}