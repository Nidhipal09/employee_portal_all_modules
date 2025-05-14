package com.employeeportal.model.onboarding;

import java.util.List;

import javax.validation.Valid;

import com.employeeportal.dto.onboarding.*;

import lombok.Data;

@Data
public class OnboardingDetails {

    @Valid
    private PersonalDetailsDTO personalDetails;

    @Valid
    private List<AddressDTO> address;

    @Valid
    private List<EducationDTO> education;

    @Valid
    private AadharCardDetailsDTO aadharCardDetails;

    @Valid
    private PanCardDetailsDTO panCardDetails;

    @Valid
    private List<ProfessionalReferencesDTO> professionalReferences;

    @Valid
    private List<RelativesDTO> relatives;

    @Valid
    private List<EmploymentHistoryDTO> employmentHistories;

    @Valid
    private PassportDetailsDTO passportDetails;

    @Valid
    private VisaDetailsDTO visaDetails;

    @Valid
    private AdditionalDetailsDTO additionalDetailsDTO;

}
