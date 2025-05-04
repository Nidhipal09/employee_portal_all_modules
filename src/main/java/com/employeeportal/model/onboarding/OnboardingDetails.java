package com.employeeportal.model.onboarding;

import java.util.List;

import com.employeeportal.dto.onboarding.*;

import lombok.Data;

@Data
public class OnboardingDetails {

    private EmployeeDTO employee;
    private PersonalDetailsDTO personalDetails;
    private List<AddressDTO> address;
    private List<EducationDTO> education;
    private EmployeeOrganizationDetailsDTO employeeOrganizationDetails;
    private List<IdentificationDetailsDTO> identificationDetails;
    private List<ProfessionalReferencesDTO> professionalReferences;
    private List<RelativesDTO> relatives;
    private List<EmploymentHistoryDTO> employmentHistories;
    private PassportDetailsDTO passportDetails;
    private VisaDetailsDTO visaDetails;
    private OtherDetailsDTO otherDetails;
    private String pageIdentifier;
}
