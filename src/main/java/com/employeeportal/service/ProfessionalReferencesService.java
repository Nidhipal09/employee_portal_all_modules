package com.employeeportal.service;

import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.ProfessionalReferences;

import java.util.List;

public interface ProfessionalReferencesService {

    ProfessionalReferences saveProfessionalReferences(ProfessionalReferences professionalReferences);
    List<ProfessionalReferences> getAllProfessionalReferences();
    ProfessionalReferences getProfessionalReferencesById(Long professionalId);
    ProfessionalReferences updateProfessionalReferencesById(Long professionalId, ProfessionalReferences professionalReferences);
    ProfessionalReferences deleteProfessionalReferencesById(Long professionalId);
}
