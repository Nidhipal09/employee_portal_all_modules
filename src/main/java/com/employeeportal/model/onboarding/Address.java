package com.employeeportal.model.onboarding;

import lombok.Data;
import javax.persistence.*;

import com.employeeportal.model.registration.Employee;

import java.sql.Date;

@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    private String addressType;
    private String address1;
    private String address2;
    private String address3;
    private String zip;
    private String state;
    private String city;
    private String country;
    private Date stayFrom;
    private Date stayTo;
    private String emergencyContactNumber;
    private String emergencyContactNameAndRelationship;

    @ManyToOne
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;

}