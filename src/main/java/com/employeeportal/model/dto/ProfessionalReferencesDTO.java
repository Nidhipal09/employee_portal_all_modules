package com.employeeportal.model.dto;

public class ProfessionalReferencesDTO {

    private Long professionalId;
    private String name;
    private String designation;
    private String email;
    private String contactNumber;

    // Default constructor
    public ProfessionalReferencesDTO() {
    }

    // Parameterized constructor
    public ProfessionalReferencesDTO(Long professionalId, String name, String designation, String email, String contactNumber) {
        this.professionalId = professionalId;
        this.name = name;
        this.designation = designation;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    // Getters and Setters
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

    @Override
    public String toString() {
        return "ProfessionalReferencesDTO{" +
                "professionalId=" + professionalId +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
