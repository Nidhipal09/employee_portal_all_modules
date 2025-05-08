package com.employeeportal.serviceImpl.onboarding;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeportal.dto.onboarding.AadharCardDetailsDTO;
import com.employeeportal.dto.onboarding.AddressDTO;
import com.employeeportal.dto.onboarding.EducationDTO;
import com.employeeportal.dto.onboarding.IdentificationDetailsDTO;
import com.employeeportal.dto.onboarding.OnboardingResponseDTO;
import com.employeeportal.dto.onboarding.OtherDetailsDTO;
import com.employeeportal.dto.onboarding.PanCardDetailsDTO;
import com.employeeportal.dto.onboarding.PassportDetailsDTO;
import com.employeeportal.dto.onboarding.PersonalDetailsDTO;
import com.employeeportal.dto.onboarding.EmploymentHistoryDTO;
import com.employeeportal.dto.onboarding.ProfessionalReferencesDTO;
import com.employeeportal.dto.onboarding.RelativesDTO;
import com.employeeportal.dto.onboarding.VisaDetailsDTO;
import com.employeeportal.exception.FieldsMissingException;
import com.employeeportal.model.onboarding.Address;
import com.employeeportal.model.onboarding.Education;
import com.employeeportal.model.onboarding.IdentificationDetails;
import com.employeeportal.model.onboarding.IdentityType;
import com.employeeportal.model.onboarding.OnboardingDetails;
import com.employeeportal.model.onboarding.OtherDetails;
import com.employeeportal.model.onboarding.PassportDetails;
import com.employeeportal.model.onboarding.PersonalDetails;
import com.employeeportal.model.onboarding.EmploymentHistory;
import com.employeeportal.model.onboarding.ProfessionalReferences;
import com.employeeportal.model.onboarding.Relatives;
import com.employeeportal.model.onboarding.VisaDetails;
import com.employeeportal.model.registration.Employee;
import com.employeeportal.repository.onboarding.AddressRepository;
import com.employeeportal.repository.onboarding.EducationRepository;
import com.employeeportal.repository.onboarding.EmploymentHistoryRepository;
import com.employeeportal.repository.onboarding.IdentificationDetailsRepository;
import com.employeeportal.repository.onboarding.OtherDetailsRepository;
import com.employeeportal.repository.onboarding.PassportDetailsRepository;
import com.employeeportal.repository.onboarding.PersonalDetailsRepository;
import com.employeeportal.repository.onboarding.ProfessionalReferencesRepository;
import com.employeeportal.repository.onboarding.RelativesRepository;
import com.employeeportal.repository.onboarding.VisaDetailsRepository;
import com.employeeportal.repository.registration.EmployeeRepository;
import com.employeeportal.service.onboarding.OnboardingService;;

@Service
public class OnboadingServiceImpl implements OnboardingService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private EmploymentHistoryRepository employmentHistoryRepository;

    @Autowired
    private IdentificationDetailsRepository identificationDetailsRepository;

    @Autowired
    private OtherDetailsRepository otherDetailsRepository;

    @Autowired
    private PassportDetailsRepository passportDetailsRepository;

    @Autowired
    private PersonalDetailsRepository personalDetailsRepository;

    @Autowired
    private ProfessionalReferencesRepository professionalReferencesRepository;

    @Autowired
    private RelativesRepository relativesRepository;

    @Autowired
    private VisaDetailsRepository visaDetailsRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public OnboardingResponseDTO fillOnboardingDetails(OnboardingDetails onboardingDetails, String email,
            String pageIdentifier) {

        Employee employee = employeeRepository.findByEmail(email);
        int employeeId = employee.getEmployeeId();

        if (pageIdentifier.equals("personalDetails")) {

            if (onboardingDetails.getPersonalDetails().isNull() ||
                    onboardingDetails.getAadharCardDetails().isNull() ||
                    onboardingDetails.getPanCardDetails().isNull() ||
                    onboardingDetails.getPassportDetails().isNull()) {
                throw new FieldsMissingException("Please fill all the mandatory fields(Personal & Identification details) in the Personal Details form.");
            }

            PersonalDetailsDTO personalDetailsDTO = onboardingDetails.getPersonalDetails();
            if (!personalDetailsDTO.isNull()) {
                PersonalDetails personalDetailsFromDB = personalDetailsRepository.findByEmployeeEmployeeId(employeeId);
                personalDetailsFromDB.setImageUrl(personalDetailsDTO.getImageUrl());
                personalDetailsFromDB.setGender(personalDetailsDTO.getGender());
                personalDetailsFromDB.setMotherName(personalDetailsDTO.getMotherName());
                personalDetailsFromDB.setFatherName(personalDetailsDTO.getFatherName());
                personalDetailsFromDB.setSecondaryMobile(personalDetailsDTO.getSecondaryMobile());
                personalDetailsRepository.save(personalDetailsFromDB);
            }

            // List<IdentificationDetailsDTO> identificationDetailsDTOs =
            // onboardingDetails.getIdentificationDetails();
            // if (!identificationDetailsDTOs.isEmpty()) {
            // List<IdentificationDetails> identificationDetailsFromDB =
            // identificationDetailsRepository.findByEmployeeEmployeeId(employeeId);
            // if (!identificationDetailsFromDB.isEmpty()) {
            // identificationDetailsRepository.deleteAllByEmployeeId(employeeId);
            // }
            // identificationDetailsDTOs.forEach(identificationDetailsDTO -> {
            // IdentificationDetails identificationDetail =
            // dtoToEntity(identificationDetailsDTO,
            // IdentificationDetails.class);
            // identificationDetail.setIdentificationNumber(identificationDetailsDTO.getAadharIdentificationNumber());
            // identificationDetail.setIdentificationUrl(identificationDetailsDTO.getAadharIdentificationUrl());
            // identificationDetail.setIdentityType(IdentityType.AADHAR);
            // identificationDetail.setEmployee(employee);
            // identificationDetailsRepository.save(identificationDetail);

            // identificationDetail.setIdentificationNumber(identificationDetailsDTO.getPanIdentificationNumber());
            // identificationDetail.setIdentificationUrl(identificationDetailsDTO.getPanIdentificationUrl());
            // identificationDetail.setIdentityType(IdentityType.PAN);
            // identificationDetail.setEmployee(employee);
            // identificationDetailsRepository.save(identificationDetail);
            // });
            // }

            AadharCardDetailsDTO aadharCardDetailsDTO = onboardingDetails.getAadharCardDetails();
            if (!aadharCardDetailsDTO.isNull()) {
                IdentificationDetails identificationDetailsFromDB = identificationDetailsRepository
                        .findByEmployeeAndType(employeeId, "AADHAR");
                if (identificationDetailsFromDB != null) {
                    identificationDetailsRepository.deleteByEmployeeIdAndType(employeeId, "AADHAR");
                }
                IdentificationDetails identificationDetails = new IdentificationDetails();
                identificationDetails.setIdentificationNumber(aadharCardDetailsDTO.getAadharIdentificationNumber());
                identificationDetails.setIdentificationUrl(aadharCardDetailsDTO.getAadharIdentificationUrl());
                identificationDetails.setIdentityType(IdentityType.AADHAR);
                identificationDetails.setEmployee(employee);
                identificationDetailsRepository.save(identificationDetails);

            }

            PanCardDetailsDTO panCardDetailsDTO = onboardingDetails.getPanCardDetails();
            if (!panCardDetailsDTO.isNull()) {
                IdentificationDetails identificationDetailsFromDB = identificationDetailsRepository
                        .findByEmployeeAndType(employeeId, "PAN");
                if (identificationDetailsFromDB != null) {
                    identificationDetailsRepository.deleteByEmployeeIdAndType(employeeId, "PAN");
                }
                IdentificationDetails identificationDetails = new IdentificationDetails();
                identificationDetails.setIdentificationNumber(panCardDetailsDTO.getPanIdentificationNumber());
                identificationDetails.setIdentificationUrl(panCardDetailsDTO.getPanIdentificationUrl());
                identificationDetails.setIdentityType(IdentityType.PAN);
                identificationDetails.setEmployee(employee);
                identificationDetailsRepository.save(identificationDetails);

            }

            PassportDetailsDTO passportDetailsDTO = onboardingDetails.getPassportDetails();
            if (!passportDetailsDTO.isNull()) {
                PassportDetails passportDetailsFromDB = passportDetailsRepository.findByEmployeeEmployeeId(employeeId);
                if (passportDetailsFromDB != null) {
                    passportDetailsFromDB.setPassportNumber(passportDetailsDTO.getPassportNumber());
                    passportDetailsFromDB.setPassportUrl(passportDetailsDTO.getPassportUrl());
                    passportDetailsRepository.save(passportDetailsFromDB);
                } else {
                    PassportDetails passportDetails = dtoToEntity(passportDetailsDTO, PassportDetails.class);
                    passportDetails.setEmployee(employee);
                    passportDetailsRepository.save(passportDetails);
                }

            }

            fetchOnboardingDetails(onboardingDetails, employeeId, "contact");
            fetchOnboardingDetails(onboardingDetails, employeeId, "education");
            fetchOnboardingDetails(onboardingDetails, employeeId, "professional");
            fetchOnboardingDetails(onboardingDetails, employeeId, "other");

        } else if (pageIdentifier.equals("contact")) {

            if (onboardingDetails.getAddress().isEmpty()) {
                throw new FieldsMissingException("Please fill all the mandatory fields(Address details) in the Contact details form.");
            }

            List<AddressDTO> addressDTOs = onboardingDetails.getAddress();
            if (!addressDTOs.isEmpty()) {
                List<Address> addressesFromDB = addressRepository.findByEmployeeEmployeeId(employeeId);
                if (!addressesFromDB.isEmpty()) {
                    addressRepository.deleteAllByEmployeeId(employeeId);
                }
                addressDTOs.forEach(addressDTO -> {
                    Address address = dtoToEntity(addressDTO, Address.class);
                    address.setEmployee(employee);
                    addressRepository.save(address);
                });
            }

            fetchOnboardingDetails(onboardingDetails, employeeId, "personalDetails");
            fetchOnboardingDetails(onboardingDetails, employeeId, "education");
            fetchOnboardingDetails(onboardingDetails, employeeId, "professional");
            fetchOnboardingDetails(onboardingDetails, employeeId, "other");

        } else if (pageIdentifier.equals("education")) {

            if (onboardingDetails.getEducation().isEmpty() ||
                    onboardingDetails.getEmploymentHistories().isEmpty() ) {
                throw new FieldsMissingException("Please fill all the mandatory fields(Education & Employment history details) in the Education details form.");
            }

            List<EducationDTO> educationDTOs = onboardingDetails.getEducation();
            if (!educationDTOs.isEmpty()) {
                List<Education> educationFromDB = educationRepository.findByEmployeeEmployeeId(employeeId);
                if (!educationFromDB.isEmpty()) {
                    educationRepository.deleteAllByEmployeeId(employeeId);
                }
                educationDTOs.forEach(educationDTO -> {
                    Education educationDetails = dtoToEntity(educationDTO, Education.class);
                    educationDetails.setEmployee(employee);
                    educationRepository.save(educationDetails);
                });
            }

            List<EmploymentHistoryDTO> employmentHistoryDTOs = onboardingDetails.getEmploymentHistories();
            if (!employmentHistoryDTOs.isEmpty()) {
                List<EmploymentHistory> employmentHistoryFromDB = employmentHistoryRepository
                        .findByEmployeeEmployeeId(employeeId);
                if (!employmentHistoryFromDB.isEmpty()) {
                    employmentHistoryRepository.deleteAllByEmployeeId(employeeId);
                }
                employmentHistoryDTOs.forEach(employmentHistoryDTO -> {
                    EmploymentHistory employmentHistory = dtoToEntity(employmentHistoryDTO, EmploymentHistory.class);
                    employmentHistory.setEmployee(employee);
                    employmentHistoryRepository.save(employmentHistory);
                });
            }

            fetchOnboardingDetails(onboardingDetails, employeeId, "personalDetails");
            fetchOnboardingDetails(onboardingDetails, employeeId, "contact");
            fetchOnboardingDetails(onboardingDetails, employeeId, "professional");
            fetchOnboardingDetails(onboardingDetails, employeeId, "other");

        } else if (pageIdentifier.equals("professional")) {

            if (onboardingDetails.getProfessionalReferences().isEmpty() ||
                    onboardingDetails.getRelatives().isEmpty() ||
                    onboardingDetails.getPassportDetails().isNull() ||
                    onboardingDetails.getVisaDetails().isNull()) {
                throw new FieldsMissingException("Please fill all the mandatory fields(Professional, Passport & Visa details) in the Professional details form.");
            }

            List<ProfessionalReferencesDTO> professionalReferencesDTOs = onboardingDetails.getProfessionalReferences();
            if (!professionalReferencesDTOs.isEmpty()) {
                List<ProfessionalReferences> professionalReferencesFromDB = professionalReferencesRepository
                        .findByEmployeeEmployeeId(employeeId);
                if (!professionalReferencesFromDB.isEmpty()) {
                    professionalReferencesRepository.deleteAllByEmployeeId(employeeId);
                }
                professionalReferencesDTOs.forEach(professionalReferencesDTO -> {
                    ProfessionalReferences professionalReference = dtoToEntity(professionalReferencesDTO,
                            ProfessionalReferences.class);
                    professionalReference.setEmployee(employee);
                    professionalReferencesRepository.save(professionalReference);
                });
            }

            List<RelativesDTO> relativesDTOs = onboardingDetails.getRelatives();
            if (!relativesDTOs.isEmpty()) {
                List<Relatives> relativesFromDB = relativesRepository.findByEmployeeEmployeeId(employeeId);
                if (!relativesFromDB.isEmpty()) {
                    relativesRepository.deleteAllByEmployeeId(employeeId);
                }
                relativesDTOs.forEach(relativesDTO -> {
                    Relatives relative = dtoToEntity(relativesDTO, Relatives.class);
                    relative.setEmployee(employee);
                    relativesRepository.save(relative);
                });
            }

            PassportDetailsDTO passportDetailsDTO = onboardingDetails.getPassportDetails();
            if (!passportDetailsDTO.isNull()) {
                PassportDetails passportDetailsFromDB = passportDetailsRepository.findByEmployeeEmployeeId(employeeId);
                if (passportDetailsFromDB != null) {
                    passportDetailsFromDB.setPassportNumber(passportDetailsDTO.getPassportNumber());
                    passportDetailsFromDB.setDateOfIssue(passportDetailsDTO.getDateOfIssue());
                    passportDetailsFromDB.setNationality(passportDetailsDTO.getNationality());
                    passportDetailsFromDB.setPlaceOfIssue(passportDetailsDTO.getPlaceOfIssue());
                    passportDetailsFromDB.setCountryOfIssue(passportDetailsDTO.getCountryOfIssue());
                    passportDetailsFromDB.setValidUpto(passportDetailsDTO.getValidUpto());
                    passportDetailsRepository.save(passportDetailsFromDB);
                } else {
                    PassportDetails passportDetails = dtoToEntity(passportDetailsDTO, PassportDetails.class);
                    passportDetails.setEmployee(employee);
                    passportDetailsRepository.save(passportDetails);
                }
            }

            VisaDetailsDTO visaDetailsDTO = onboardingDetails.getVisaDetails();
            if (!visaDetailsDTO.isNull()) {
                VisaDetails visaDetailsDB = visaDetailsRepository.findByEmployeeEmployeeId(employeeId);
                if (visaDetailsDB != null) {
                    visaDetailsDB.setCountry(visaDetailsDTO.getCountry());
                    visaDetailsDB.setPassportCopy(visaDetailsDTO.getPassportCopy());
                    visaDetailsDB.setPassportCopyUrl(visaDetailsDTO.getPassportCopyUrl());
                    visaDetailsDB.setStatus(visaDetailsDTO.getStatus());
                    visaDetailsDB.setWorkPermitDetails(visaDetailsDTO.getWorkPermitDetails());
                    visaDetailsDB.setWorkPermitValidTill(visaDetailsDTO.getWorkPermitValidTill());
                    visaDetailsRepository.save(visaDetailsDB);
                } else {
                    VisaDetails visaDetails = dtoToEntity(visaDetailsDTO, VisaDetails.class);
                    visaDetails.setEmployee(employee);
                    visaDetailsRepository.save(visaDetails);
                }
            }

            fetchOnboardingDetails(onboardingDetails, employeeId, "personalDetails");
            fetchOnboardingDetails(onboardingDetails, employeeId, "education");
            fetchOnboardingDetails(onboardingDetails, employeeId, "contact");
            fetchOnboardingDetails(onboardingDetails, employeeId, "other");

        } else if (pageIdentifier.equals("other")) {

            if (onboardingDetails.getOtherDetails().isNull()) {
                throw new FieldsMissingException("Please fill all the mandatory fields(Other details) in the Other details form.");
            }

            OtherDetailsDTO otherDetailsDTO = onboardingDetails.getOtherDetails();
            if (!otherDetailsDTO.isNull()) {
                OtherDetails otherDetailsFromDB = otherDetailsRepository.findByEmployeeEmployeeId(employeeId);
                if (otherDetailsFromDB != null) {
                    otherDetailsFromDB.setHobbiesDeclaration(otherDetailsDTO.getHobbiesDeclaration());
                    otherDetailsFromDB.setIllnessDeclaration(otherDetailsDTO.getIllnessDeclaration());
                    otherDetailsRepository.save(otherDetailsFromDB);
                } else {
                    OtherDetails otherDetails = dtoToEntity(otherDetailsDTO, OtherDetails.class);
                    otherDetails.setEmployee(employee);
                    otherDetailsRepository.save(otherDetails);
                }
            }

            fetchOnboardingDetails(onboardingDetails, employeeId, "personalDetails");
            fetchOnboardingDetails(onboardingDetails, employeeId, "education");
            fetchOnboardingDetails(onboardingDetails, employeeId, "contact");
            fetchOnboardingDetails(onboardingDetails, employeeId, "professional");

        }

        return new OnboardingResponseDTO(onboardingDetails, employee.getStatus(), pageIdentifier);

    }

    private <D, E> E dtoToEntity(D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    private <E, D> D entityToDto(E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    private <E, D> List<D> entityListToDtoList(List<E> entityList, Class<D> dtoClass) {
        if (entityList == null) {
            return null;
        }
        return entityList.stream()
                .map(entity -> entityToDto(entity, dtoClass))
                .collect(Collectors.toList());
    }

    @Override
    public OnboardingResponseDTO getOnboardingDetails(String email, String pageIdentifier) {
        OnboardingDetails onboardingDetails = new OnboardingDetails();

        Employee employee = employeeRepository.findByEmail(email);
        int employeeId = employee.getEmployeeId();

        fetchOnboardingDetails(onboardingDetails, employeeId, pageIdentifier);

        return new OnboardingResponseDTO(onboardingDetails, employee.getStatus(), pageIdentifier);
    }

    private void fetchOnboardingDetails(OnboardingDetails onboardingDetails,
            int employeeId, String pageIdentifier) {
        if (pageIdentifier.equals("personalDetails")) {
            PersonalDetails personalDetails = personalDetailsRepository.findByEmployeeEmployeeId(employeeId);
            if (personalDetails != null) {
                PersonalDetailsDTO personalDetailsDTO = entityToDto(personalDetails, PersonalDetailsDTO.class);

                Employee employee = employeeRepository.findByEmployeeId(employeeId);
                String firstName = employee.getFirstName() == null ? "" : employee.getFirstName() + " ";
                String middleName = employee.getMiddleName() == null ? "" : employee.getMiddleName() + " ";
                String lastName = employee.getLastName() == null ? "" : employee.getLastName();
                String fullName = firstName + middleName + lastName;
                personalDetailsDTO.setFullName(fullName);
                onboardingDetails.setPersonalDetails(personalDetailsDTO);
            }

            // Fetch and set identification details
            List<IdentificationDetails> identificationDetailsList = identificationDetailsRepository
                    .findByEmployeeEmployeeId(employeeId);
            if (!identificationDetailsList.isEmpty()) {
                identificationDetailsList.forEach(identificationDetails -> {
                    if (identificationDetails.getIdentityType() == IdentityType.AADHAR) {
                        AadharCardDetailsDTO aadharCardDetailsDTO = new AadharCardDetailsDTO();
                        aadharCardDetailsDTO
                                .setAadharIdentificationNumber(identificationDetails.getIdentificationNumber());
                        aadharCardDetailsDTO.setAadharIdentificationUrl(identificationDetails.getIdentificationUrl());
                        onboardingDetails.setAadharCardDetails(aadharCardDetailsDTO);
                    }
                    if (identificationDetails.getIdentityType() == IdentityType.PAN) {
                        PanCardDetailsDTO panCardDetailsDTO = new PanCardDetailsDTO();
                        panCardDetailsDTO.setPanIdentificationNumber(identificationDetails.getIdentificationNumber());
                        panCardDetailsDTO.setPanIdentificationUrl(identificationDetails.getIdentificationUrl());
                        onboardingDetails.setPanCardDetails(panCardDetailsDTO);
                    }
                });
            }

            PassportDetails passportDetails = passportDetailsRepository.findByEmployeeEmployeeId(employeeId);
            if (passportDetails != null) {
                PassportDetailsDTO passportDetailsDTO = entityToDto(passportDetails, PassportDetailsDTO.class);
                onboardingDetails.setPassportDetails(passportDetailsDTO);
            }
        } else if (pageIdentifier.equals("contact")) {

            // Fetch and set Address details
            List<Address> addressList = addressRepository.findByEmployeeEmployeeId(employeeId);
            if (!addressList.isEmpty())
                onboardingDetails.setAddress(entityListToDtoList(addressList, AddressDTO.class));

        } else if (pageIdentifier.equals("education")) {

            // Fetch and set Education details
            List<Education> educationList = educationRepository.findByEmployeeEmployeeId(employeeId);
            if (!educationList.isEmpty())
                onboardingDetails.setEducation(entityListToDtoList(educationList, EducationDTO.class));

            // Fetch and set Employment History details
            List<EmploymentHistory> employmentHistoryList = employmentHistoryRepository
                    .findByEmployeeEmployeeId(employeeId);
            if (!employmentHistoryList.isEmpty())
                onboardingDetails.setEmploymentHistories(
                        entityListToDtoList(employmentHistoryList, EmploymentHistoryDTO.class));

        } else if (pageIdentifier.equals("professional")) {
            // Fetch and set Professional References details
            List<ProfessionalReferences> professionalReferencesList = professionalReferencesRepository
                    .findByEmployeeEmployeeId(employeeId);
            if (!professionalReferencesList.isEmpty())
                onboardingDetails.setProfessionalReferences(
                        entityListToDtoList(professionalReferencesList, ProfessionalReferencesDTO.class));

            // Fetch and set Relatives details
            List<Relatives> relativesList = relativesRepository.findByEmployeeEmployeeId(employeeId);
            if (!relativesList.isEmpty())
                onboardingDetails.setRelatives(entityListToDtoList(relativesList, RelativesDTO.class));

            PassportDetails passportDetails = passportDetailsRepository.findByEmployeeEmployeeId(employeeId);
            if (passportDetails != null) {
                PassportDetailsDTO passportDetailsDTO = entityToDto(passportDetails, PassportDetailsDTO.class);
                onboardingDetails.setPassportDetails(passportDetailsDTO);
            }

            VisaDetails visaDetails = visaDetailsRepository.findByEmployeeEmployeeId(employeeId);
            if (visaDetails != null) {
                VisaDetailsDTO visaDetailsDTO = entityToDto(visaDetails, VisaDetailsDTO.class);
                onboardingDetails.setVisaDetails(visaDetailsDTO);
            }

        } else if (pageIdentifier.equals("other")) {
            // Fetch and set Other details
            OtherDetails otherDetails = otherDetailsRepository.findByEmployeeEmployeeId(employeeId);
            if (otherDetails != null) {
                OtherDetailsDTO otherDetailsDTO = entityToDto(otherDetails, OtherDetailsDTO.class);
                onboardingDetails.setOtherDetails(otherDetailsDTO);
            }
        }

    }

}
