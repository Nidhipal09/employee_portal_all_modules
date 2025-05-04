package com.employeeportal.model;

import javax.persistence.*;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "primary_details")
public class PrimaryDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int primaryId;
    private String fullName;
    private String mobileNumber;
    private String dateOfBirth;
    private String email;
    private String roleName;
    private String password;
    private LocalDateTime createdDate;
    @OneToOne(mappedBy = "primaryDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "personal_id", nullable = true)
    private PersonalDetails personalDetails;

    @OneToOne(mappedBy = "primaryDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "permanent_id", nullable = true)
    private PermanentAddress permanentAddress;

    @OneToOne(mappedBy = "primaryDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "current_id")
    private CurrentAddress currentAddresses;

	@OneToMany(mappedBy = "primaryDetails", cascade = CascadeType.ALL)
	private List<AddressDetails> addressDetails;

	@OneToMany(mappedBy = "primaryDetails", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<EducationalQualification> educationalQualifications;

    @OneToMany(mappedBy = "primaryDetails", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<DocumentCertificates> documentCertificates;

	@OneToMany(mappedBy = "primaryDetails", cascade = CascadeType.ALL)
	private List<EmploymentHistory> employmentHistories;

	@OneToMany(mappedBy = "primaryDetails", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<ProfessionalReferences> professionalReferences;
    @OneToOne(mappedBy = "primaryDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "employee_relative_id", nullable = true)
    private EmployeeRelative employeeRelative;    // Mapping to EmployeeRelative
	@OneToOne(mappedBy = "primaryDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "passport_id", nullable = true)
	private PassportDetails passportDetails;
    @OneToOne(mappedBy = "primaryDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "visa_id", nullable = true)
    private VisaStatus visaStatus;
    @OneToOne(mappedBy = "primaryDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "work_id", nullable = true)
    private WorkPermit workPermit;
    @OneToOne(mappedBy = "primaryDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "other_id", nullable = true)
    private OtherDetails otherDetails;

    @OneToMany(mappedBy = "primaryDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Leave> leave;

    


}

