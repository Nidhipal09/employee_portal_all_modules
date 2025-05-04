package com.employeeportal.model.onboarding;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int educationId;
    private String degreeName;
    private String subject;
    private String passingYear;
    private String rollNumber;
    private String gradeOrPercentage;
    private String passing_certificate;
    private String degree_certificate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;

}
