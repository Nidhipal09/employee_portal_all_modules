package com.employeeportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name="other_details")
public class OtherDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long otherId;
    private String illness;
    private String selfIntroduction;
    private Boolean declarationAccepted;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "primary_id", nullable = false)
    private PrimaryDetails primaryDetails;


}


