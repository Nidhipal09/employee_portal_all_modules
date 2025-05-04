package com.employeeportal.model.onboarding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "personal_details")
public class PersonalDetails {

    private String imageUrl;
    private String personalEmail;
    private String gender;
    private String motherName;
    private String fatherName;
    private String secondaryMobile;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "employeeId", nullable = false) 
    private Employee employee;

}
