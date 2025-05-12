package com.employeeportal.model.onboarding;

import com.employeeportal.model.registration.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name = "passport_details")
public class PassportDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int passportId;
    private String passportNumber;
    private LocalDate dateOfIssue;
    private String placeOfIssue;
    private String countryOfIssue;
    private LocalDate validUpto;
    private String nationality;
    private String passportUrl;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "employeeId", nullable = false) 
    private Employee employee; 

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue == null ? null
                : LocalDate.parse(dateOfIssue, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getDateOfIssue() {
        return (dateOfIssue != null) ? dateOfIssue.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;
    }

    public void setValidUpto(String validUpto) {
        this.validUpto = validUpto == null ? null
                : LocalDate.parse(validUpto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getValidUpto() {
        return (validUpto != null) ? validUpto.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;
    }
}
