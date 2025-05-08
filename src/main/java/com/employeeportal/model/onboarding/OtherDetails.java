package com.employeeportal.model.onboarding;

import com.employeeportal.model.registration.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "other_details")
public class OtherDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int otherId;
    private String illnessDeclaration;
    private String hobbiesDeclaration;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;

}
