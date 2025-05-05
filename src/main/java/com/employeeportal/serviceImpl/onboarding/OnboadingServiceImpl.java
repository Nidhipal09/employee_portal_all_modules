package com.employeeportal.serviceImpl.onboarding;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeportal.dto.onboarding.AddressDTO;
import com.employeeportal.dto.onboarding.EducationDTO;
import com.employeeportal.dto.onboarding.IdentificationDetailsDTO;
import com.employeeportal.dto.onboarding.OtherDetailsDTO;
import com.employeeportal.dto.onboarding.PassportDetailsDTO;
import com.employeeportal.dto.onboarding.PersonalDetailsDTO;
import com.employeeportal.dto.onboarding.EmploymentHistoryDTO;
import com.employeeportal.dto.onboarding.ProfessionalReferencesDTO;
import com.employeeportal.dto.onboarding.RelativesDTO;
import com.employeeportal.dto.onboarding.VisaDetailsDTO;
import com.employeeportal.model.onboarding.Address;
import com.employeeportal.model.onboarding.Education;
import com.employeeportal.model.onboarding.IdentificationDetails;
import com.employeeportal.model.onboarding.OnboardingDetails;
import com.employeeportal.model.onboarding.OtherDetails;
import com.employeeportal.model.onboarding.PassportDetails;
import com.employeeportal.model.onboarding.PersonalDetails;
import com.employeeportal.model.onboarding.EmploymentHistory;
import com.employeeportal.model.onboarding.ProfessionalReferences;
import com.employeeportal.model.onboarding.Relatives;
import com.employeeportal.model.onboarding.VisaDetails;
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

    @Override
    public void fillOnboardingDetails(OnboardingDetails onboardingDetails) {

        String pageIdentifier = onboardingDetails.getPageIdentifier();

        if (pageIdentifier.equals("personalDetails")) {

            PersonalDetailsDTO personalDetailsDTO = onboardingDetails.getPersonalDetails();
            PersonalDetails personalDetails = dtoToEntity(personalDetailsDTO, PersonalDetails.class);
            personalDetails.setPersonalEmail(onboardingDetails.getEmployee().getEmail());
            personalDetailsRepository.save(personalDetails);

            List<IdentificationDetailsDTO> identificationDetailsDTOs = onboardingDetails.getIdentificationDetails();
            identificationDetailsDTOs.forEach(identificationDetailsDTO -> {
                IdentificationDetails identificationDetail = dtoToEntity(identificationDetailsDTO,
                        IdentificationDetails.class);
                identificationDetailsRepository.save(identificationDetail);
            });

            PassportDetailsDTO passportDetailsDTO = onboardingDetails.getPassportDetails();
            PassportDetails passportDetails = dtoToEntity(passportDetailsDTO, PassportDetails.class);
            passportDetailsRepository.save(passportDetails);

        } else if (pageIdentifier.equals("contact")) {

            List<AddressDTO> addressDTOs = onboardingDetails.getAddress();
            addressDTOs.forEach(addressDTO -> {
                Address address = dtoToEntity(addressDTO, Address.class);
                addressRepository.save(address);
            });

        } else if (pageIdentifier.equals("education")) {

            List<EducationDTO> educationDTOs = onboardingDetails.getEducation();
            educationDTOs.forEach(educationDTO -> {
                Education educationDetails = dtoToEntity(educationDTO, Education.class);
                educationRepository.save(educationDetails);
            });

            List<EmploymentHistoryDTO> employmentHistoryDTOs = onboardingDetails.getEmploymentHistories();
            employmentHistoryDTOs.forEach(employmentHistoryDTO -> {
                EmploymentHistory employmentHistory = dtoToEntity(employmentHistoryDTO, EmploymentHistory.class);
                employmentHistoryRepository.save(employmentHistory);
            });

        } else if (pageIdentifier.equals("professional")) {

            List<ProfessionalReferencesDTO> professionalReferencesDTOs = onboardingDetails.getProfessionalReferences();
            professionalReferencesDTOs.forEach(professionalReferencesDTO -> {
                ProfessionalReferences professionalReference = dtoToEntity(professionalReferencesDTO,
                        ProfessionalReferences.class);
                professionalReferencesRepository.save(professionalReference);
            });

            List<RelativesDTO> relativesDTOs = onboardingDetails.getRelatives();
            relativesDTOs.forEach(relativesDTO -> {
                Relatives relative = dtoToEntity(relativesDTO, Relatives.class);
                relativesRepository.save(relative);
            });

            PassportDetailsDTO passportDetailsDTO = onboardingDetails.getPassportDetails();
            String passportNumber = passportDetailsDTO.getPassportNumber();
            PassportDetails passportDetails = passportDetailsRepository.findByPassportNumber(passportNumber);
            passportDetails.setDateOfIssue(passportDetailsDTO.getDateOfIssue());
            passportDetails.setNationality(passportDetailsDTO.getNationality());
            passportDetails.setPlaceOfIssue(passportDetailsDTO.getPlaceOfIssue());
            passportDetails.setCountryOfIssue(passportDetailsDTO.getCountryOfIssue());
            passportDetails.setValidUpto(passportDetailsDTO.getValidUpto());
            passportDetailsRepository.save(passportDetails);

            VisaDetailsDTO visaDetailsDTO = onboardingDetails.getVisaDetails();
            VisaDetails visaDetails = dtoToEntity(visaDetailsDTO, VisaDetails.class);
            visaDetailsRepository.save(visaDetails);

        } else if (pageIdentifier.equals("other")) {

            OtherDetailsDTO otherDetailsDTO = onboardingDetails.getOtherDetails();
            OtherDetails otherDetails = dtoToEntity(otherDetailsDTO, OtherDetails.class);
            otherDetailsRepository.save(otherDetails);

        }

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
    public OnboardingDetails getOnboardingDetails(int employeeId) {
        OnboardingDetails onboardingDetails = new OnboardingDetails();

        // Fetch and set personal details
        PersonalDetails personalDetails = personalDetailsRepository.findByEmployeeId(employeeId);
        if (personalDetails != null) {
            PersonalDetailsDTO personalDetailsDTO = entityToDto(personalDetails, PersonalDetailsDTO.class);
            onboardingDetails.setPersonalDetails(personalDetailsDTO);
        }

        // Fetch and set identification details 
        List<IdentificationDetails> identificationDetailsList = identificationDetailsRepository.findByEmployeeId(employeeId);
        onboardingDetails.setIdentificationDetails(entityListToDtoList(identificationDetailsList, IdentificationDetailsDTO.class));

        // Fetch and set Address details
        List<Address> addressList = addressRepository.findByEmployeeId(employeeId);
        onboardingDetails.setAddress(entityListToDtoList(addressList, AddressDTO.class));

        // Fetch and set Education details
        List<Education> educationList = educationRepository.findByEmployeeId(employeeId);
        onboardingDetails.setEducation(entityListToDtoList(educationList, EducationDTO.class));

        // Fetch and set Employment History details
        List<EmploymentHistory> employmentHistoryList = employmentHistoryRepository.findByEmployeeId(employeeId);
        onboardingDetails.setEmploymentHistories(entityListToDtoList(employmentHistoryList, EmploymentHistoryDTO.class));

        // Fetch and set Professional References details
        List<ProfessionalReferences> professionalReferencesList = professionalReferencesRepository.findByEmployeeId(employeeId);
        onboardingDetails.setProfessionalReferences(entityListToDtoList(professionalReferencesList, ProfessionalReferencesDTO.class));

        // Fetch and set Relatives details
        List<Relatives> relativesList = relativesRepository.findByEmployeeId(employeeId);
        onboardingDetails.setRelatives(entityListToDtoList(relativesList, RelativesDTO.class));

        // Fetch and set Passport details
        PassportDetails passportDetails = passportDetailsRepository.findByEmployeeId(employeeId);
        if (passportDetails != null) {
            PassportDetailsDTO passportDetailsDTO = entityToDto(passportDetails, PassportDetailsDTO.class);
            onboardingDetails.setPassportDetails(passportDetailsDTO);
        }

        // Fetch and set Visa details
        VisaDetails visaDetails = visaDetailsRepository.findByEmployeeId(employeeId);
        if (visaDetails != null) {
            VisaDetailsDTO visaDetailsDTO = entityToDto(visaDetails, VisaDetailsDTO.class);
            onboardingDetails.setVisaDetails(visaDetailsDTO);
        }

        // Fetch and set Other details
        OtherDetails otherDetails = otherDetailsRepository.findByEmployeeId(employeeId);
        if (otherDetails != null) {
            OtherDetailsDTO otherDetailsDTO = entityToDto(otherDetails, OtherDetailsDTO.class);
            onboardingDetails.setOtherDetails(otherDetailsDTO);
        }

        return onboardingDetails;
    }
}
