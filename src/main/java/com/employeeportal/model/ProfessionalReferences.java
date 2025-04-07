package com.employeeportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="professional_details")
public class ProfessionalReferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long professionalId;
    private String name;
    private String designation;
    private String email;
    private String contactNumber;
    @ManyToOne
    @JsonIgnore// Assuming many references can be linked to one primary detail
    @JoinColumn(name = "primary_id", nullable = false) // Foreign key column in the ProfessionalReferences table
    private PrimaryDetails primaryDetails;

    public ProfessionalReferences() {
    }

    public ProfessionalReferences(Long professionalId, String name, String designation, String email, String contactNumber) {
        this.professionalId = professionalId;
        this.name = name;
        this.designation = designation;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    public Long getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(Long professionalId) {
        this.professionalId = professionalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public PrimaryDetails getPrimaryDetails() {
        return primaryDetails;
    }

    public void setPrimaryDetails(PrimaryDetails primaryDetails) {
        this.primaryDetails = primaryDetails;
    }
}
