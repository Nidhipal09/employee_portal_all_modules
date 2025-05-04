package com.employeeportal.model.onboarding;

import lombok.Data;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.sql.Date;

@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mobileNumber;
    private Date dateOfBirth;
    private String email;
    private String password;
    private String status;
    private LocalDateTime createdDate;
    
    @OneToOne(mappedBy = "employee")
    private PersonalDetails personalDetails;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Address> addressesDetails;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Education> educationDetails;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<IdentificationDetails> identificationDetails;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmployeeOrganizationDetails employeeOrganizationDetails;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private PassportDetails passportDetails;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private VisaDetails visaDetails;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<ProfessionalReferences> professionalReferences;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Relatives> relatives;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<OtherDetails> otherDetails;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmploymentHistory> employmentHistories;


}
