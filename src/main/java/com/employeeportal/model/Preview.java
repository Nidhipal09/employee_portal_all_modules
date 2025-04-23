package com.employeeportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name="preview")
public class Preview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long previewId;
    private String employeeSignatureUrl;
    private String signatureDate;
    private String place;
    @OneToOne
    @JsonIgnore// Assuming one-to-one relationship with PrimaryDetails
    @JoinColumn(name = "primary_id") // Foreign key column in the Preview table
    private PrimaryDetails primaryDetails;

}
