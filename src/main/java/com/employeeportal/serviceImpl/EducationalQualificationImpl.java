package com.employeeportal.serviceImpl;

import com.employeeportal.exception.BadRequestException;
import com.employeeportal.exception.NotFoundException;
import com.employeeportal.model.*;
import com.employeeportal.model.dto.DocumentCertificateDTO;
import com.employeeportal.model.dto.EducationResponseDTO;
import com.employeeportal.model.dto.EducationalQualificationDTO;
import com.employeeportal.model.dto.EmploymentHistoryDTO;
import com.employeeportal.repository.*;
import com.employeeportal.service.EducationalQualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EducationalQualificationImpl  implements EducationalQualificationService {
    @Autowired
    private EducationalQualificationRepository educationalQualificationRepo;
    @Autowired
    private DocumentCertificatesRepository documentCertificatesRepository;
    @Autowired
    private EmploymentHistoryRepository employmentHistoryRepository;
    @Autowired
    private PrimaryDetailsRepository primaryDetailsRepository;

    public EducationResponseDTO saveEducationalQualification(EducationDTO detail) {
        Optional<PrimaryDetails> primary = primaryDetailsRepository.findById(detail.getPrimaryId());
        if(!primary.isPresent()){
            throw new NotFoundException("user not found ");
        }
        List<EducationalQualification> ed = educationalQualificationRepo.findAllByPrimaryDetails(primary.get());
        List<DocumentCertificates> doc = documentCertificatesRepository.findAllByPrimaryDetails(primary.get());
        List<EmploymentHistory> emp = employmentHistoryRepository.findAllByPrimaryDetails(primary.get());
        if(!ed.isEmpty() && !doc.isEmpty() && !emp.isEmpty()){
            throw new BadRequestException(" history already exists");
        }
        List<EducationalQualification> educationalQualifications = new ArrayList<>();
        List<EducationalQualification> educational = new ArrayList<>();
        for (EducationalQualificationDTO eduDTO : detail.getEducationalQualifications()) {
            EducationalQualification edu = new EducationalQualification();
            edu.setDegreeName(eduDTO.getDegreeName());
            edu.setSubject(eduDTO.getSubject());
            edu.setPassingYear(eduDTO.getPassingYear());
            edu.setRollNumber(eduDTO.getRollNumber());
            edu.setGradeOrPercentage(eduDTO.getGradeOrPercentage());
            edu.setPrimaryDetails(primary.get());
            educationalQualifications.add(edu);
        }
        educational = educationalQualificationRepo.saveAll(educationalQualifications);

        List<DocumentCertificates> documentCertificates = new ArrayList<>();
        List<DocumentCertificates> docu = new ArrayList<>();

        for (DocumentCertificateDTO dto : detail.getDocuments()) {
            DocumentCertificates document = new DocumentCertificates();
            document.setDocumentType(dto.getDocumentType());
            document.setDegreeCertificate(dto.getDegreeCertificate());
            document.setDegreeCertificateUrl(dto.getDegreeCertificateUrl());
            document.setPassingCertificate(dto.getPassingCertificate());
            document.setPassingCertificateUrl(dto.getPassingCertificateUrl());
            document.setPrimaryDetails(primary.get());
            documentCertificates.add(document);
        }
        docu = documentCertificatesRepository.saveAll(documentCertificates);

        List<EmploymentHistory> empl = new ArrayList<>();
        List<EmploymentHistory> employmentHistories = new ArrayList<>();
        for (EmploymentHistoryDTO empDTO : detail.getEmploymentHistories()) {
            EmploymentHistory employmentHistory = new EmploymentHistory();
            employmentHistory.setPreviousEmployerName(empDTO.getPreviousEmployerName());
            employmentHistory.setEmployerAddress(empDTO.getEmployerAddress());
            employmentHistory.setTelephoneNumber(empDTO.getTelephoneNumber());
            employmentHistory.setEmployeeCode(empDTO.getEmployeeCode());
            employmentHistory.setDesignation(empDTO.getDesignation());
            employmentHistory.setDepartment(empDTO.getDepartment());
            employmentHistory.setManagerName(empDTO.getManagerName());
            employmentHistory.setManagerEmail(empDTO.getManagerEmail());
            employmentHistory.setManagerContactNo(empDTO.getManagerContactNo());
            employmentHistory.setReasonsForLeaving(empDTO.getReasonsForLeaving());
            employmentHistory.setEmploymentStartDate(empDTO.getEmploymentStartDate());
            employmentHistory.setEmploymentEndDate(empDTO.getEmploymentEndDate());
            employmentHistory.setEmploymentType(empDTO.getEmploymentType());
            employmentHistory.setExperienceCertificateUrl(empDTO.getExperienceCertificateUrl());
            employmentHistory.setRelievingLetterUrl(empDTO.getRelievingLetterUrl());
            employmentHistory.setLastMonthSalarySlipUrl(empDTO.getLastMonthSalarySlipUrl());
            employmentHistory.setAppointmentLetterUrl(empDTO.getAppointmentLetterUrl());
            employmentHistory.setPrimaryDetails(primary.get());
            employmentHistories.add(employmentHistory);
        }
        empl = employmentHistoryRepository.saveAll(employmentHistories);
        EducationResponseDTO  response = new EducationResponseDTO();
        response.setEducationalQualificationList(educational);
        response.setEmploymentHistories(empl);
        response.setDocumentCertificates(docu);
        return response;

    }


    @Override
    public List<EducationalQualification> getAllEducationalQualification() {
        List<EducationalQualification> educationalQualifications = educationalQualificationRepo.findAll();
        return educationalQualifications;
    }

    @Override
    public EducationalQualification getEducationalQualificationById(Long educationalId) {
        EducationalQualification educationalQualification = educationalQualificationRepo.findById(educationalId).orElse(null);
        return educationalQualification;
    }

    @Override
    public EducationalQualification updateEducationalQualificationById(Long educationalId, EducationalQualification educationalQualification) {
        // Fetch the existing EducationalQualification by ID
        EducationalQualification existingEducationalQualification = educationalQualificationRepo.findById(educationalId)
                .orElseThrow(() -> new RuntimeException("Educational Qualification not found for ID: " + educationalId));

        // Update fields if the new value is valid (not null or blank)
        existingEducationalQualification.setDegreeName(isValidValue(educationalQualification.getDegreeName()) ? educationalQualification.getDegreeName() : existingEducationalQualification.getDegreeName());
        existingEducationalQualification.setSubject(isValidValue(educationalQualification.getSubject()) ? educationalQualification.getSubject() : existingEducationalQualification.getSubject());
        existingEducationalQualification.setPassingYear(isValidValue(educationalQualification.getPassingYear()) ? educationalQualification.getPassingYear() : existingEducationalQualification.getPassingYear());
        existingEducationalQualification.setRollNumber(isValidValue(educationalQualification.getRollNumber()) ? educationalQualification.getRollNumber() : existingEducationalQualification.getRollNumber());
        existingEducationalQualification.setGradeOrPercentage(isValidValue(educationalQualification.getGradeOrPercentage()) ? educationalQualification.getGradeOrPercentage() : existingEducationalQualification.getGradeOrPercentage());

        // Save the updated EducationalQualification
        educationalQualificationRepo.save(existingEducationalQualification);

        // Return the updated EducationalQualification
        return existingEducationalQualification;
    }

    // Helper method to check if a string value is not null or blank
    private boolean isValidValue(String value) {
        return value != null && !value.trim().isEmpty(); // Checks both null and blank string
    }


    @Override
    public EducationalQualification deleteEducationalQualificationById(Long educationalId) {
        EducationalQualification educationalQualificationDelete = educationalQualificationRepo.findById(educationalId).orElse(null);
        if (educationalQualificationDelete != null) {
            educationalQualificationRepo.delete(educationalQualificationDelete);
            return educationalQualificationDelete;
        } else {
            throw new RuntimeException("EducationalQualifications not found with id: " + educationalId);
        }

    }

    @Override
    public EducationResponseDTO updateEducationalQualification(EducationDTO educationDTO) {
        // 1. Update Educational Qualification List
        List<EducationalQualification> updatedQualifications = new ArrayList<>();
        if (!ObjectUtils.isEmpty(educationDTO.getEducationalQualifications())) {
            for (EducationalQualificationDTO qualificationDTO : educationDTO.getEducationalQualifications()) {
                Optional<EducationalQualification> existingOpt = educationalQualificationRepo.findByEducationalId(qualificationDTO.getEducationalId());

                if (existingOpt.isPresent()) {
                    EducationalQualification existing = existingOpt.get();

                    existing.setDegreeName(isValidValue(qualificationDTO.getDegreeName()) ? qualificationDTO.getDegreeName() : existing.getDegreeName());
                    existing.setSubject(isValidValue(qualificationDTO.getSubject()) ? qualificationDTO.getSubject() : existing.getSubject());
                    existing.setPassingYear(isValidValue(qualificationDTO.getPassingYear()) ? qualificationDTO.getPassingYear() : existing.getPassingYear());
                    existing.setRollNumber(isValidValue(qualificationDTO.getRollNumber()) ? qualificationDTO.getRollNumber() : existing.getRollNumber());
                    existing.setGradeOrPercentage(isValidValue(qualificationDTO.getGradeOrPercentage()) ? qualificationDTO.getGradeOrPercentage() : existing.getGradeOrPercentage());

                    updatedQualifications.add(educationalQualificationRepo.save(existing));
                }
            }
        }

        List<DocumentCertificates> updatedDocuments = new ArrayList<>();

        if (!ObjectUtils.isEmpty(educationDTO.getDocuments())) {
            for (DocumentCertificateDTO docDTO : educationDTO.getDocuments()) {
                Optional<DocumentCertificates> existingDocOpt = documentCertificatesRepository.findByDocumentId(docDTO.getDocumentId());

                if (existingDocOpt.isPresent()) {
                    DocumentCertificates existing = existingDocOpt.get();

                    existing.setDegreeCertificate(docDTO.getDegreeCertificate() != null ? docDTO.getDegreeCertificate() : existing.getDegreeCertificate());
                    existing.setDegreeCertificateUrl(isValidValue(docDTO.getDegreeCertificateUrl()) ? docDTO.getDegreeCertificateUrl() : existing.getDegreeCertificateUrl());
                    existing.setDocumentType(docDTO.getDocumentType() != null ? docDTO.getDocumentType() : existing.getDocumentType());
                    existing.setPassingCertificate(docDTO.getPassingCertificate() != null ? docDTO.getPassingCertificate() : existing.getPassingCertificate());
                    existing.setPassingCertificateUrl(isValidValue(docDTO.getPassingCertificateUrl()) ? docDTO.getPassingCertificateUrl() : existing.getPassingCertificateUrl());

                    updatedDocuments.add(documentCertificatesRepository.save(existing));
                }
            }
        }

        // 3. Update Education History
        List<EmploymentHistory> updatedHistoryList = new ArrayList<>();

        if (!ObjectUtils.isEmpty(educationDTO.getEmploymentHistories())) {
            for (EmploymentHistoryDTO historyDTO : educationDTO.getEmploymentHistories()) {
                Optional<EmploymentHistory> existingHistoryOpt = employmentHistoryRepository.findByEmploymentId(historyDTO.getEmploymentId());

                if (existingHistoryOpt.isPresent()) {
                    EmploymentHistory existing = existingHistoryOpt.get();

                    existing.setPreviousEmployerName(isValidValue(historyDTO.getPreviousEmployerName()) ? historyDTO.getPreviousEmployerName() : existing.getPreviousEmployerName());
                    existing.setEmployerAddress(isValidValue(historyDTO.getEmployerAddress()) ? historyDTO.getEmployerAddress() : existing.getEmployerAddress());
                    existing.setTelephoneNumber(isValidValue(historyDTO.getTelephoneNumber()) ? historyDTO.getTelephoneNumber() : existing.getTelephoneNumber());
                    existing.setEmployeeCode(isValidValue(historyDTO.getEmployeeCode()) ? historyDTO.getEmployeeCode() : existing.getEmployeeCode());
                    existing.setDesignation(isValidValue(historyDTO.getDesignation()) ? historyDTO.getDesignation() : existing.getDesignation());
                    existing.setDepartment(isValidValue(historyDTO.getDepartment()) ? historyDTO.getDepartment() : existing.getDepartment());
                    existing.setManagerName(isValidValue(historyDTO.getManagerName()) ? historyDTO.getManagerName() : existing.getManagerName());
                    existing.setManagerEmail(isValidValue(historyDTO.getManagerEmail()) ? historyDTO.getManagerEmail() : existing.getManagerEmail());
                    existing.setManagerContactNo(isValidValue(historyDTO.getManagerContactNo()) ? historyDTO.getManagerContactNo() : existing.getManagerContactNo());
                    existing.setReasonsForLeaving(isValidValue(historyDTO.getReasonsForLeaving()) ? historyDTO.getReasonsForLeaving() : existing.getReasonsForLeaving());
                    existing.setEmploymentStartDate(historyDTO.getEmploymentStartDate() != null ? historyDTO.getEmploymentStartDate() : existing.getEmploymentStartDate());
                    existing.setEmploymentEndDate(historyDTO.getEmploymentEndDate() != null ? historyDTO.getEmploymentEndDate() : existing.getEmploymentEndDate());
                    existing.setEmploymentType(isValidValue(historyDTO.getEmploymentType()) ? historyDTO.getEmploymentType() : existing.getEmploymentType());
                    existing.setExperienceCertificateUrl(isValidValue(historyDTO.getExperienceCertificateUrl()) ? historyDTO.getExperienceCertificateUrl() : existing.getExperienceCertificateUrl());
                    existing.setRelievingLetterUrl(isValidValue(historyDTO.getRelievingLetterUrl()) ? historyDTO.getRelievingLetterUrl() : existing.getRelievingLetterUrl());
                    existing.setLastMonthSalarySlipUrl(isValidValue(historyDTO.getLastMonthSalarySlipUrl()) ? historyDTO.getLastMonthSalarySlipUrl() : existing.getLastMonthSalarySlipUrl());
                    existing.setAppointmentLetterUrl(isValidValue(historyDTO.getAppointmentLetterUrl()) ? historyDTO.getAppointmentLetterUrl() : existing.getAppointmentLetterUrl());

                    updatedHistoryList.add(employmentHistoryRepository.save(existing));
                }
            }
        }


        // Prepare the response
        EducationResponseDTO response = new EducationResponseDTO();
        response.setEducationalQualificationList(updatedQualifications);
        response.setDocumentCertificates(updatedDocuments);
        response.setEmploymentHistories(updatedHistoryList);

        return response;
    }


}
