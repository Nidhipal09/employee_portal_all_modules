package com.employeeportal.service;

import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.ProfessionalReferences;
import com.employeeportal.model.dto.ProfessionalRequestDTO;
import com.employeeportal.model.dto.ProfessionalResponseDTO;

import java.util.List;

public interface ProfessionalReferencesService {

    ProfessionalResponseDTO saveProfessionalReferences(ProfessionalRequestDTO professionalReferences);
    List<ProfessionalReferences> getAllProfessionalReferences();
    ProfessionalReferences getProfessionalReferencesById(Long professionalId);
    ProfessionalReferences updateProfessionalReferencesById(Long professionalId, ProfessionalReferences professionalReferences);
    ProfessionalReferences deleteProfessionalReferencesById(Long professionalId);

    ProfessionalResponseDTO updateProfessionalReferences(ProfessionalRequestDTO professionalReferences);
}
