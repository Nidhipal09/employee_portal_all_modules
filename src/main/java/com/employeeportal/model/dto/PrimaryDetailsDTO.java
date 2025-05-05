package com.employeeportal.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PrimaryDetailsDTO {
    private String fullName;
    private String mobileNumber;
    private String dateOfBirth;
    private String email;
    private String roleName;
    private String password;
    private LocalDateTime createdDate;
    private PersonalDetailsDTO personalDetailsDTO;
    private List<AddressDetailsDTO> addressDetails;
    private List<EducationalQualificationDTO> educationalQualifications;
    private List<DocumentCertificateDTO> documents;
    private List<EmploymentHistoryDTO> employmentHistories;
    private OtherDetailsDTO otherDetails;

    // New fields added based on PrimaryDetails
    private CurrentAddressDTO currentAddresses; // List for current addresses
    private PermanentAddressDTO permanentAddress; // One-to-One for permanent address
    private PreviewDTO preview; // One-to-One for preview details
    private List<ProfessionalReferencesDTO> professionalReferences; // List for professional references
    private PassportDetailsDTO passportDetails;
    private EmployeeRelativeDTO employeeRelatives;
    private List<RelativeInfoDTO> relativeInfos;
    private VisaStatusDTO visaStatus;
    private WorkPermitDTO workPermit;
    private EmployeeDetailsDTO employeeDetails;

    public PrimaryDetailsDTO() {
    }

    public PrimaryDetailsDTO(String fullName, String mobileNumber, String dateOfBirth, String email, String roleName,
            String password, LocalDateTime createdDate, PersonalDetailsDTO personalDetailsDTO,
            List<AddressDetailsDTO> addressDetails, List<EducationalQualificationDTO> educationalQualifications,
            List<DocumentCertificateDTO> documents, List<EmploymentHistoryDTO> employmentHistories,
            OtherDetailsDTO otherDetails, CurrentAddressDTO currentAddresses, PermanentAddressDTO permanentAddress,
            PreviewDTO preview, List<ProfessionalReferencesDTO> professionalReferences,
            PassportDetailsDTO passportDetails, EmployeeRelativeDTO employeeRelatives,
            List<RelativeInfoDTO> relativeInfos, VisaStatusDTO visaStatus, WorkPermitDTO workPermit,
            EmployeeDetailsDTO employeeDetails) {
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.roleName = roleName;
        this.password = password;
        this.createdDate = createdDate;
        this.personalDetailsDTO = personalDetailsDTO;
        this.addressDetails = addressDetails;
        this.educationalQualifications = educationalQualifications;
        this.documents = documents;
        this.employmentHistories = employmentHistories;
        this.otherDetails = otherDetails;
        this.currentAddresses = currentAddresses;
        this.permanentAddress = permanentAddress;
        this.preview = preview;
        this.professionalReferences = professionalReferences;
        this.passportDetails = passportDetails;
        this.employeeRelatives = employeeRelatives;
        this.relativeInfos = relativeInfos;
        this.visaStatus = visaStatus;
        this.workPermit = workPermit;
        this.employeeDetails = employeeDetails;
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

    public PersonalDetailsDTO getPersonalDetailsDTO() {
        return personalDetailsDTO;
    }

    public void setPersonalDetailsDTO(PersonalDetailsDTO personalDetailsDTO) {
        this.personalDetailsDTO = personalDetailsDTO;
    }

    public List<AddressDetailsDTO> getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(List<AddressDetailsDTO> addressDetails) {
        this.addressDetails = addressDetails;
    }

    public List<EducationalQualificationDTO> getEducationalQualifications() {
        return educationalQualifications;
    }

    public void setEducationalQualifications(List<EducationalQualificationDTO> educationalQualifications) {
        this.educationalQualifications = educationalQualifications;
    }

    public List<DocumentCertificateDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentCertificateDTO> documents) {
        this.documents = documents;
    }

    public List<EmploymentHistoryDTO> getEmploymentHistories() {
        return employmentHistories;
    }

    public void setEmploymentHistories(List<EmploymentHistoryDTO> employmentHistories) {
        this.employmentHistories = employmentHistories;
    }

    public OtherDetailsDTO getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(OtherDetailsDTO otherDetails) {
        this.otherDetails = otherDetails;
    }

    public CurrentAddressDTO getCurrentAddresses() {
        return currentAddresses;
    }

    public void setCurrentAddresses(CurrentAddressDTO currentAddresses) {
        this.currentAddresses = currentAddresses;
    }

    public PermanentAddressDTO getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(PermanentAddressDTO permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public PreviewDTO getPreview() {
        return preview;
    }

    public void setPreview(PreviewDTO preview) {
        this.preview = preview;
    }

    public List<ProfessionalReferencesDTO> getProfessionalReferences() {
        return professionalReferences;
    }

    public void setProfessionalReferences(List<ProfessionalReferencesDTO> professionalReferences) {
        this.professionalReferences = professionalReferences;
    }

    public PassportDetailsDTO getPassportDetails() {
        return passportDetails;
    }

    public void setPassportDetails(PassportDetailsDTO passportDetails) {
        this.passportDetails = passportDetails;
    }

    public EmployeeRelativeDTO getEmployeeRelatives() {
        return employeeRelatives;
    }

    public void setEmployeeRelatives(EmployeeRelativeDTO employeeRelatives) {
        this.employeeRelatives = employeeRelatives;
    }

    public List<RelativeInfoDTO> getRelativeInfos() {
        return relativeInfos;
    }

    public void setRelativeInfos(List<RelativeInfoDTO> relativeInfos) {
        this.relativeInfos = relativeInfos;
    }

    public VisaStatusDTO getVisaStatus() {
        return visaStatus;
    }

    public void setVisaStatus(VisaStatusDTO visaStatus) {
        this.visaStatus = visaStatus;
    }

    public WorkPermitDTO getWorkPermit() {
        return workPermit;
    }

    public void setWorkPermit(WorkPermitDTO workPermit) {
        this.workPermit = workPermit;
    }

    public EmployeeDetailsDTO getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(EmployeeDetailsDTO employeeDetails) {
        this.employeeDetails = employeeDetails;
    }
}
