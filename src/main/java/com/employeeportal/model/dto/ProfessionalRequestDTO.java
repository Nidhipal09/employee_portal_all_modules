package com.employeeportal.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProfessionalRequestDTO {
    private Long primaryId;
    private List<ProfessionalReferencesDTO> professionalReferences; // List for professional references
    private PassportDetailsDTO passportDetails;
    private EmployeeRelativeDTO employeeRelatives;
    private VisaStatusDTO visaStatus;
    private WorkPermitDTO workPermit;

}
