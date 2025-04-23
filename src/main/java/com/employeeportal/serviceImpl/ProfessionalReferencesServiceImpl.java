package com.employeeportal.serviceImpl;

import com.employeeportal.exception.BadRequestException;
import com.employeeportal.exception.NotFoundException;
import com.employeeportal.model.*;
import com.employeeportal.model.dto.*;
import com.employeeportal.repository.*;
import com.employeeportal.service.PrimaryDetailsService;
import com.employeeportal.service.ProfessionalReferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessionalReferencesServiceImpl implements ProfessionalReferencesService {
    @Autowired
    private ProfessionalReferencesRepository professionalReferencesRepo;
    @Autowired
    private PassportDetailsRepository passportDetailsRepository;
    @Autowired
    private EmployeeRelativeRepository employeeRelativeRepository;
    @Autowired
    private WorkPermitRepository workPermitRepository;
    @Autowired
    private VisaStatusRepository visaStatusRepository;
    @Autowired
    private RelativeInfoRepository relativeInfoRepository;
    @Autowired
    private PrimaryDetailsRepository primaryDetailsRepository;

    @Override
    public ProfessionalResponseDTO saveProfessionalReferences(ProfessionalRequestDTO professional) {
        Optional<PrimaryDetails> primaryDetails = primaryDetailsRepository.findById(professional.getPrimaryId());
        if (!primaryDetails.isPresent()) {
            throw new NotFoundException("user not found");
        }
        List<ProfessionalReferences> professionalReferences = professionalReferencesRepo.findAllByPrimaryDetails(primaryDetails.get());
        Optional<PassportDetails> passportDetails = passportDetailsRepository.findByPrimaryDetails(primaryDetails.get());
        Optional<VisaStatus> visaStatus = visaStatusRepository.findByPrimaryDetails(primaryDetails.get());
        Optional<WorkPermit> workPermit = workPermitRepository.findByPrimaryDetails(primaryDetails.get());
        Optional<EmployeeRelative> employeeRelative = employeeRelativeRepository.findByPrimaryDetails(primaryDetails.get());
        if (visaStatus.isPresent() && workPermit.isPresent() && employeeRelative.isPresent() && passportDetails.isPresent() && !professionalReferences.isEmpty()) {
            throw new NotFoundException(" data already exists");
        }
        List<ProfessionalReferences> references = addProfessionalReferences(professional.getProfessionalReferences(), primaryDetails.get());
        PassportDetails passport = addPassportDetails(professional.getPassportDetails(), primaryDetails.get());
        VisaStatus visa = addVisaStatus(professional.getVisaStatus(), primaryDetails.get());
        WorkPermit work = addWorkPermit(professional.getWorkPermit(), primaryDetails.get());
        EmployeeRelative empl = addEmployeeRelative(professional.getEmployeeRelatives(), primaryDetails.get());
        ProfessionalResponseDTO responseDTO = new ProfessionalResponseDTO();
        responseDTO.setProfessionalReferences(references);
        responseDTO.setPassportDetails(passport);
        responseDTO.setEmployeeRelative(empl);
        responseDTO.setVisaStatus(visa);
        responseDTO.setWorkPermit(work);
        return responseDTO;

    }

    private List<ProfessionalReferences> addProfessionalReferences(List<ProfessionalReferencesDTO> referencesDTOs, PrimaryDetails primaryDetails) {
        List<ProfessionalReferences> professionalReferences = new ArrayList<>();
        for (ProfessionalReferencesDTO referenceDTO : referencesDTOs) {
            ProfessionalReferences reference = new ProfessionalReferences();
            reference.setProfessionalId(referenceDTO.getProfessionalId()); // Assuming you want to set this
            reference.setName(referenceDTO.getName());
            reference.setDesignation(referenceDTO.getDesignation());
            reference.setEmail(referenceDTO.getEmail());
            reference.setContactNumber(referenceDTO.getContactNumber());
            reference.setPrimaryDetails(primaryDetails); // Associate with primary details
            professionalReferences.add(reference);
        }
        return professionalReferencesRepo.saveAll(professionalReferences);
    }

    private List<RelativeInfo> addRelativeInfo(List<RelativeInfoDTO> relativeInfoDTOs, EmployeeRelative employeeRelative) {
        List<RelativeInfo> relativeInfoList = new ArrayList<>();
        for (RelativeInfoDTO relativeInfoDTO : relativeInfoDTOs) {
            RelativeInfo relativeInfo = new RelativeInfo();
            relativeInfo.setName(relativeInfoDTO.getName());
            relativeInfo.setEmployeeId(relativeInfoDTO.getEmployeeId());
            relativeInfo.setRelationship(relativeInfoDTO.getRelationship());
            relativeInfo.setDepartment(relativeInfoDTO.getDepartment());
            relativeInfo.setLocation(relativeInfoDTO.getLocation());
            relativeInfo.setRemarks(relativeInfoDTO.getRemarks());
            relativeInfo.setEmployeeRelative(employeeRelative);  // Set the relationship with PrimaryDetails

            relativeInfoList.add(relativeInfo);
        }

        return relativeInfoRepository.saveAll(relativeInfoList);
    }

    private PassportDetails addPassportDetails(PassportDetailsDTO passportDetailsDTO, PrimaryDetails primaryDetails) {

        PassportDetails passportDetails = new PassportDetails();
        passportDetails.setPassportNumber(passportDetailsDTO.getPassportNumber());
        passportDetails.setIssueDate(passportDetailsDTO.getIssueDate()); // Adjusted field name
        passportDetails.setPlaceOfIssue(passportDetailsDTO.getPlaceOfIssue());
        passportDetails.setExpiryDate(passportDetailsDTO.getExpiryDate());
        passportDetails.setCountryOfIssue(passportDetailsDTO.getCountryOfIssue());
        passportDetails.setNationality(passportDetailsDTO.getNationality());
        passportDetails.setPrimaryDetails(primaryDetails); // Associate with primary details
        return passportDetailsRepository.save(passportDetails);
    }

    private EmployeeRelative addEmployeeRelative(EmployeeRelativeDTO employeeRelativeDTO, PrimaryDetails primaryDetails) {
        EmployeeRelative employeeRelative = new EmployeeRelative();
        employeeRelative.setHasRelative(employeeRelativeDTO.getHasRelative());
        employeeRelative.setPrimaryDetails(primaryDetails);
        EmployeeRelative employee = employeeRelativeRepository.save(employeeRelative);
        employeeRelative.setRelativeInfoList(addRelativeInfo(employeeRelativeDTO.getRelativeInfoDTOS(), employee));
        return employee;
    }

    private VisaStatus addVisaStatus(VisaStatusDTO visaStatusDTO, PrimaryDetails primaryDetails) {
        VisaStatus visaStatus = new VisaStatus();
        visaStatus.setCitizen(visaStatusDTO.getCitizen());  // Fixed method call
        visaStatus.setExpatOnGreenCard(visaStatusDTO.getExpatOnGreenCard());
        visaStatus.setExpatOnWorkPermit(visaStatusDTO.getExpatOnWorkPermit());
        visaStatus.setExpatOnPermanentResidencyPermit(visaStatusDTO.getExpatOnPermanentResidencyPermit());
        visaStatus.setAnyOtherStatus(visaStatusDTO.getAnyOtherStatus());
        visaStatus.setPrimaryDetails(primaryDetails);
        return visaStatusRepository.save(visaStatus);
    }

    private WorkPermit addWorkPermit(WorkPermitDTO workPermitDTO, PrimaryDetails primaryDetails) {
        WorkPermit workPermit = new WorkPermit();
        workPermit.setLegalRightToWork(workPermitDTO.getLegalRightToWork());
        workPermit.setWorkPermitDetails(workPermitDTO.getWorkPermitDetails());
        workPermit.setWorkPermitValidTill(workPermitDTO.getWorkPermitValidTill());
        workPermit.setPassportCopy(workPermitDTO.getPassportCopy());
        workPermit.setPassportCopyPath(workPermitDTO.getPassportCopyPath());
        workPermit.setPrimaryDetails(primaryDetails);  // Associate with primary details
        return workPermitRepository.save(workPermit);
    }


    @Override
    public List<ProfessionalReferences> getAllProfessionalReferences() {
        List<ProfessionalReferences> professionalReferences = professionalReferencesRepo.findAll();
        return professionalReferences;
    }

    @Override
    public ProfessionalReferences getProfessionalReferencesById(Long professionalId) {
        ProfessionalReferences professionalReferences = professionalReferencesRepo.findById(professionalId).orElse(null);
        return professionalReferences;
    }


    @Override
    public ProfessionalReferences updateProfessionalReferencesById(Long professionalId, ProfessionalReferences professionalReferences) {
        // Fetch the existing professional references by ID
        ProfessionalReferences existingProfessionalReferences = professionalReferencesRepo.findById(professionalId).orElse(null);

        // Check if the existing professional references were found
        if (existingProfessionalReferences != null) {
            // Update the existing professional references with the values from the incoming professionalReferences object
            existingProfessionalReferences.setName(isValidValue(professionalReferences.getName())
                    ? professionalReferences.getName()
                    : existingProfessionalReferences.getName());

            existingProfessionalReferences.setDesignation(isValidValue(professionalReferences.getDesignation())
                    ? professionalReferences.getDesignation()
                    : existingProfessionalReferences.getDesignation());

            existingProfessionalReferences.setEmail(isValidValue(professionalReferences.getEmail())
                    ? professionalReferences.getEmail()
                    : existingProfessionalReferences.getEmail());

            existingProfessionalReferences.setContactNumber(isValidValue(professionalReferences.getContactNumber())
                    ? professionalReferences.getContactNumber()
                    : existingProfessionalReferences.getContactNumber());

            // Save the updated professional references
            professionalReferencesRepo.save(existingProfessionalReferences);

            // Return the updated professional references
            return existingProfessionalReferences;
        } else {
            // If no matching professional references were found, return null or throw an exception
            return null; // Or throw a custom exception here if necessary, e.g., throw new NotFoundException("Professional references not found.");
        }
    }

    // Helper method to check if a string value is not null or blank
    private boolean isValidValue(String value) {
        return value != null && !value.trim().isEmpty();
    }


    @Override
    public ProfessionalReferences deleteProfessionalReferencesById(Long professionalId) {
        ProfessionalReferences professionalReferencesDelete = professionalReferencesRepo.findById(professionalId).orElse(null);
        if (professionalReferencesDelete != null) {
            professionalReferencesRepo.delete(professionalReferencesDelete);
            return professionalReferencesDelete;
        } else {
            throw new RuntimeException("ProfessionalReferences not found with id: " + professionalId);
        }
    }

    @Override
    public ProfessionalResponseDTO updateProfessionalReferences(ProfessionalRequestDTO professionalReferences) {

        // Update Professional References
        List<ProfessionalReferences> updatedReferences = new ArrayList<>();
        for (ProfessionalReferencesDTO refDTO : professionalReferences.getProfessionalReferences()) {
            Optional<ProfessionalReferences> existingRefOpt = professionalReferencesRepo.findById(refDTO.getProfessionalId());
            if (existingRefOpt.isPresent()) {
                ProfessionalReferences ref = existingRefOpt.get();
                ref.setName(isValidValue(refDTO.getName()) ? refDTO.getName() : ref.getName());
                ref.setDesignation(isValidValue(refDTO.getDesignation()) ? refDTO.getDesignation() : ref.getDesignation());
                ref.setEmail(isValidValue(refDTO.getEmail()) ? refDTO.getEmail() : ref.getEmail());
                ref.setContactNumber(isValidValue(refDTO.getContactNumber()) ? refDTO.getContactNumber() : ref.getContactNumber());
                updatedReferences.add(professionalReferencesRepo.save(ref));
            }
        }

        // Update Passport Details
        PassportDetailsDTO passportDTO = professionalReferences.getPassportDetails();
        Optional<PassportDetails> passportDetailsOpt = passportDetailsRepository.findByPassportId(passportDTO.getPassportId());
        PassportDetails passport = passportDetailsOpt.get();
        if (passportDetailsOpt.isPresent()) {

            passport.setPassportNumber(isValidValue(passportDTO.getPassportNumber()) ? passportDTO.getPassportNumber() : passport.getPassportNumber());
            passport.setIssueDate(passportDTO.getIssueDate() != null ? passportDTO.getIssueDate() : passport.getIssueDate());
            passport.setPlaceOfIssue(isValidValue(passportDTO.getPlaceOfIssue()) ? passportDTO.getPlaceOfIssue() : passport.getPlaceOfIssue());
            passport.setExpiryDate(passportDTO.getExpiryDate() != null ? passportDTO.getExpiryDate() : passport.getExpiryDate());
            passport.setCountryOfIssue(isValidValue(passportDTO.getCountryOfIssue()) ? passportDTO.getCountryOfIssue() : passport.getCountryOfIssue());
            passport.setNationality(isValidValue(passportDTO.getNationality()) ? passportDTO.getNationality() : passport.getNationality());
            passport = passportDetailsRepository.save(passport);
        }
        // Update Visa Status
        VisaStatusDTO visaDTO = professionalReferences.getVisaStatus();
        Optional<VisaStatus> visaStatusOpt = visaStatusRepository.findByVisaId(visaDTO.getVisaId());
        VisaStatus visa = visaStatusOpt.get();
        if (visaStatusOpt.isPresent()) {
            visa.setCitizen(visaDTO.getCitizen() != null ? visaDTO.getCitizen() : visa.getCitizen());
            visa.setExpatOnGreenCard(visaDTO.getExpatOnGreenCard() != null ? visaDTO.getExpatOnGreenCard() : visa.getExpatOnGreenCard());
            visa.setExpatOnWorkPermit(visaDTO.getExpatOnWorkPermit() != null ? visaDTO.getExpatOnWorkPermit() : visa.getExpatOnWorkPermit());
            visa.setExpatOnPermanentResidencyPermit(visaDTO.getExpatOnPermanentResidencyPermit() != null ? visaDTO.getExpatOnPermanentResidencyPermit() : visa.getExpatOnPermanentResidencyPermit());
            visa.setAnyOtherStatus(visaDTO.getAnyOtherStatus() != null ? visaDTO.getAnyOtherStatus() : visa.getAnyOtherStatus());
            visa = visaStatusRepository.save(visa);
        }

        // Update Work Permit
        WorkPermitDTO workPermitDTO = professionalReferences.getWorkPermit();
        Optional<WorkPermit> workPermitOpt = workPermitRepository.findByWorkId(workPermitDTO.getWorkId());
        WorkPermit workPermit = workPermitOpt.get();
        if (workPermitOpt.isPresent()) {

            workPermit.setLegalRightToWork(workPermitDTO.getLegalRightToWork() != null ? workPermitDTO.getLegalRightToWork() : workPermit.getLegalRightToWork());
            workPermit.setWorkPermitDetails(isValidValue(workPermitDTO.getWorkPermitDetails()) ? workPermitDTO.getWorkPermitDetails() : workPermit.getWorkPermitDetails());
            workPermit.setWorkPermitValidTill(workPermitDTO.getWorkPermitValidTill() != null ? workPermitDTO.getWorkPermitValidTill() : workPermit.getWorkPermitValidTill());
            workPermit.setPassportCopy(isValidValue(workPermitDTO.getPassportCopy()) ? workPermitDTO.getPassportCopy() : workPermit.getPassportCopy());
            workPermit.setPassportCopyPath(isValidValue(workPermitDTO.getPassportCopyPath()) ? workPermitDTO.getPassportCopyPath() : workPermit.getPassportCopyPath());
            workPermit = workPermitRepository.save(workPermit);
        }
        // Update Employee Relative and Relative Info
        EmployeeRelativeDTO employeeRelativeDTO = professionalReferences.getEmployeeRelatives();
        Optional<EmployeeRelative> employeeRelativeOpt = employeeRelativeRepository.findByEmployeeRelativeId(employeeRelativeDTO.getEmployeeRelativeId());
        EmployeeRelative employeeRelative = employeeRelativeOpt.get();
        if (employeeRelativeOpt.isPresent()) {

            employeeRelative.setHasRelative(employeeRelativeDTO.getHasRelative() != null ? employeeRelativeDTO.getHasRelative() : employeeRelative.getHasRelative());
            employeeRelative.setRelativeInfoList(updateRelativeInfo(employeeRelativeDTO.getRelativeInfoDTOS(), employeeRelativeDTO.getHasRelative(),employeeRelative));
            employeeRelative = employeeRelativeRepository.save(employeeRelative);
        }

        // Set response
        ProfessionalResponseDTO responseDTO = new ProfessionalResponseDTO();
        responseDTO.setProfessionalReferences(updatedReferences);
        responseDTO.setPassportDetails(passport);
        responseDTO.setVisaStatus(visa);
        responseDTO.setWorkPermit(workPermit);
        responseDTO.setEmployeeRelative(employeeRelative);

        return responseDTO;
    }

    private List<RelativeInfo> updateRelativeInfo(List<RelativeInfoDTO> relativeInfoList, Boolean hasRelative,EmployeeRelative employeeRelativeId) {
        List<RelativeInfo> relInfo = new ArrayList<>();
        if (hasRelative) {

            for (RelativeInfoDTO infoDTO : relativeInfoList) {
                Optional<RelativeInfo> existingInfoOpt = relativeInfoRepository.findById(infoDTO.getRelativeId());
                if (existingInfoOpt.isPresent()) {
                    RelativeInfo info = existingInfoOpt.get();
                    info.setName(isValidValue(infoDTO.getName()) ? infoDTO.getName() : info.getName());
                    info.setEmployeeId(isValidValue(infoDTO.getEmployeeId()) ? infoDTO.getEmployeeId() : info.getEmployeeId());
                    info.setRelationship(isValidValue(infoDTO.getRelationship()) ? infoDTO.getRelationship() : info.getRelationship());
                    info.setDepartment(isValidValue(infoDTO.getDepartment()) ? infoDTO.getDepartment() : info.getDepartment());
                    info.setLocation(isValidValue(infoDTO.getLocation()) ? infoDTO.getLocation() : info.getLocation());
                    info.setRemarks(isValidValue(infoDTO.getRemarks()) ? infoDTO.getRemarks() : info.getRemarks());
                    relInfo.add(relativeInfoRepository.save(info));


                }else{
                    RelativeInfo obj = new RelativeInfo();
                    obj.setEmployeeRelative(employeeRelativeId);
                    obj.setName(infoDTO.getName());
                    obj.setLocation(infoDTO.getLocation());
                    obj.setDepartment(infoDTO.getDepartment());
                    obj.setRemarks(infoDTO.getRemarks());
                    obj.setEmployeeId(infoDTO.getEmployeeId());
                    obj.setRelationship(infoDTO.getRelationship());
                    relInfo.add(relativeInfoRepository.save(obj));
                }
            }
        }else{
            List<RelativeInfo> info = relativeInfoRepository.findAllByEmployeeRelative(employeeRelativeId);
            if(!info.isEmpty()){
                relativeInfoRepository.deleteAll(info);
            }
        }
        return relInfo;

    }

}




