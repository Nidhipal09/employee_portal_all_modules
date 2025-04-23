package com.employeeportal.model.dto;

import com.employeeportal.model.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PrimaryPreviewResponse {
    private Long primaryId;
    private String fullName;
    private String mobileNumber;
    private String dateOfBirth;
    private String email;
    private String roleName;
    private LocalDateTime createdDate;
    private PersonalDetails personalDetails;
    private List<AddressDetails> addressDetails;
    private List<EducationalQualification> educationalQualifications;
    private List<DocumentCertificates> documents;
    private List<EmploymentHistory> employmentHistories;
    private OtherDetails otherDetails;

    // New fields added based on PrimaryDetails
    private CurrentAddress currentAddresses; // List for current addresses
    private PermanentAddress permanentAddress; // One-to-One for permanent address
    private List<ProfessionalReferences> professionalReferences; // List for professional references
    private PassportDetails passportDetails;
    private EmployeeRelative employeeRelatives;
    private VisaStatus visaStatus;
    private WorkPermit workPermit;

}
