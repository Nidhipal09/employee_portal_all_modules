package com.employeeportal.model.onboarding;

import com.employeeportal.model.registration.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.time.LocalDate;

import javax.persistence.*;

import org.apache.tomcat.jni.Local;

@Data
@Entity
@Table(name="education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int educationId;
    private String degreeName;
    private String institutionName;
    private String location;
    private String fieldOfStudy;
    private String rollNumber;
    private String gradeOrCGPA;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String passingCertificateUrl;
    private String degreeCertificateUrl;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;

}
