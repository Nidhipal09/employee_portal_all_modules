package com.employeeportal.model.dto;

import com.employeeportal.model.*;
import lombok.Data;

import java.util.List;

@Data
public class ProfessionalResponseDTO {
    private List<ProfessionalReferences> professionalReferences;
    private EmployeeRelative employeeRelative;
    private PassportDetails passportDetails;
    private VisaStatus visaStatus;
    private WorkPermit workPermit;
}
