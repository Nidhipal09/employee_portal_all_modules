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
    private String illness_declaration;
    private String hobbies_declaration;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;

}
