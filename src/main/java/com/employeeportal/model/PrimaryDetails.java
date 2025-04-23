package com.employeeportal.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "primary_details")
public class PrimaryDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long primaryId;
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

    public PrimaryDetails() {

    }

    public PrimaryDetails(Long primaryId, String fullName, String mobileNumber, String dateOfBirth, String email, String roleName, String password, LocalDateTime createdDate, PersonalDetails personalDetails, PermanentAddress permanentAddress, CurrentAddress currentAddresses, List<AddressDetails> addressDetails, List<EducationalQualification> educationalQualifications, List<DocumentCertificates> documentCertificates, List<EmploymentHistory> employmentHistories, List<ProfessionalReferences> professionalReferences, EmployeeRelative employeeRelative, PassportDetails passportDetails, VisaStatus visaStatus, WorkPermit workPermit, OtherDetails otherDetails,List<Leave>  leave) {
        this.primaryId = primaryId;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.roleName = roleName;
        this.password = password;
        this.createdDate = createdDate;
        this.personalDetails = personalDetails;
        this.permanentAddress = permanentAddress;
        this.currentAddresses = currentAddresses;
        this.addressDetails = addressDetails;
        this.educationalQualifications = educationalQualifications;
        this.documentCertificates = documentCertificates;
        this.employmentHistories = employmentHistories;
        this.professionalReferences = professionalReferences;
        this.employeeRelative = employeeRelative;
        this.passportDetails = passportDetails;
        this.visaStatus = visaStatus;
        this.workPermit = workPermit;
        this.otherDetails = otherDetails;
        this.leave = leave;
    }

    public Long getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(Long primaryId) {
        this.primaryId = primaryId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public PermanentAddress getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(PermanentAddress permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public CurrentAddress getCurrentAddresses() {
        return currentAddresses;
    }

    public void setCurrentAddresses(CurrentAddress currentAddresses) {
        this.currentAddresses = currentAddresses;
    }

    public List<AddressDetails> getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(List<AddressDetails> addressDetails) {
        this.addressDetails = addressDetails;
    }

    public List<EducationalQualification> getEducationalQualifications() {
        return educationalQualifications;
    }

    public void setEducationalQualifications(List<EducationalQualification> educationalQualifications) {
        this.educationalQualifications = educationalQualifications;
    }

    public List<DocumentCertificates> getDocumentCertificates() {
        return documentCertificates;
    }

    public void setDocumentCertificates(List<DocumentCertificates> documentCertificates) {
        this.documentCertificates = documentCertificates;
    }

    public List<EmploymentHistory> getEmploymentHistories() {
        return employmentHistories;
    }

    public void setEmploymentHistories(List<EmploymentHistory> employmentHistories) {
        this.employmentHistories = employmentHistories;
    }

    public List<ProfessionalReferences> getProfessionalReferences() {
        return professionalReferences;
    }

    public void setProfessionalReferences(List<ProfessionalReferences> professionalReferences) {
        this.professionalReferences = professionalReferences;
    }

    public EmployeeRelative getEmployeeRelative() {
        return employeeRelative;
    }

    public void setEmployeeRelative(EmployeeRelative employeeRelative) {
        this.employeeRelative = employeeRelative;
    }

    public PassportDetails getPassportDetails() {
        return passportDetails;
    }

    public void setPassportDetails(PassportDetails passportDetails) {
        this.passportDetails = passportDetails;
    }

    public VisaStatus getVisaStatus() {
        return visaStatus;
    }

    public void setVisaStatus(VisaStatus visaStatus) {
        this.visaStatus = visaStatus;
    }

    public WorkPermit getWorkPermit() {
        return workPermit;
    }

    public void setWorkPermit(WorkPermit workPermit) {
        this.workPermit = workPermit;
    }

    public OtherDetails getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(OtherDetails otherDetails) {
        this.otherDetails = otherDetails;
    }

    public List<Leave> getLeave() {
        return leave;
    }

    public void setLeave(List<Leave> leave) {
        this.leave = leave;
    }


}

