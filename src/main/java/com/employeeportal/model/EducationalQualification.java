package com.employeeportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="educational_qualification")
public class EducationalQualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long educationalId;
    private String degreeName;
    private String subject;
    private String passingYear;
    private String rollNumber;
    private String gradeOrPercentage;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "primary_id", nullable = false)
    private PrimaryDetails primaryDetails;

    public EducationalQualification() {
    }

    public EducationalQualification(String passingYear, Long educationalId, String degreeName, String subject, String rollNumber, String gradeOrPercentage, PrimaryDetails primaryDetails) {
        this.passingYear = passingYear;
        this.educationalId = educationalId;
        this.degreeName = degreeName;
        this.subject = subject;
        this.rollNumber = rollNumber;
        this.gradeOrPercentage = gradeOrPercentage;
        this.primaryDetails = primaryDetails;
    }

    public Long getEducationalId() {
        return educationalId;
    }

    public void setEducationalId(Long educationalId) {
        this.educationalId = educationalId;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(String passingYear) {
        this.passingYear = passingYear;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getGradeOrPercentage() {
        return gradeOrPercentage;
    }

    public void setGradeOrPercentage(String gradeOrPercentage) {
        this.gradeOrPercentage = gradeOrPercentage;
    }

    public PrimaryDetails getPrimaryDetails() {
        return primaryDetails;
    }

    public void setPrimaryDetails(PrimaryDetails primaryDetails) {
        this.primaryDetails = primaryDetails;
    }
}
