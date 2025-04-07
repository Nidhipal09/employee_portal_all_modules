package com.employeeportal.model.dto;
public class EducationalQualificationDTO {
    private Long educationalId;
    private String degreeName;
    private String subject;
    private String passingYear;
    private String rollNumber;
    private String gradeOrPercentage;


    public EducationalQualificationDTO() {
    }

    public EducationalQualificationDTO(Long educationalId, String degreeName, String subject, String passingYear, String rollNumber, String gradeOrPercentage) {
        this.educationalId = educationalId;
        this.degreeName = degreeName;
        this.subject = subject;
        this.passingYear = passingYear;
        this.rollNumber = rollNumber;
        this.gradeOrPercentage = gradeOrPercentage;
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
}
