package com.employeeportal.serviceImpl.registration;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.employeeportal.exception.AlreadyExistsException;
import com.employeeportal.exception.EncryptionException;
import com.employeeportal.exception.MobileNumberAlreadyExistsException;
import com.employeeportal.exception.NotFoundException;
import com.employeeportal.model.*;
import com.employeeportal.model.onboarding.PersonalDetails;
import com.employeeportal.model.onboarding.Role;
import com.employeeportal.model.registration.Employee;
import com.employeeportal.model.registration.EmployeeReg;
import com.employeeportal.model.registration.EmployeeStatus;
import com.employeeportal.repository.*;
import com.employeeportal.repository.onboarding.PersonalDetailsRepository;
import com.employeeportal.repository.onboarding.RoleRepository;
import com.employeeportal.repository.registration.EmployeeRepository;
import com.employeeportal.util.EncryptionUtil;
import com.employeeportal.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.employeeportal.config.EmailConstant;
import com.employeeportal.dto.registration.RegistrationRequest;
import com.employeeportal.dto.registration.RegistrationRequestDTO;
import com.employeeportal.dto.registration.RegistrationResponseDTO;
import com.employeeportal.dto.registration.ValidateOtpDto;
import com.employeeportal.dto.registration.ValidateTokenResponseDto;
import com.employeeportal.service.EmailService;
import com.employeeportal.service.registration.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final EmployeeRepository employeeRepository;

    private final PersonalDetailsRepository personalDetailsRepository;

    private final RoleRepository roleRepository;

    private final JwtRepository jwtRepository;
    private final JwtUtil jwtUtil;

    private final EmailService emailService;
    // private final EmployeeRelativeRepository employeeRelativeRepository;
    // private final PersonalDetailsRepository personalDetailsRepository;
    // private final PermanentAddressRepository permanentAddressRepository;
    // private final CurrentAddressRepository currentAddressRepository;
    // private final AddressDetailsRepository addressDetailsRepository;
    private final PasswordEncoder passwordEncoder;
    // private final BCryptPasswordEncoder passwordEncoder = new
    // BCryptPasswordEncoder();
    private final RedisTemplate<String, Object> redisTemplate;
    // private final DocumentCertificatesRepository documentCertificatesRepository;
    // private final EducationalQualificationRepository
    // educationalQualificationRepository;
    // private final ProfessionalReferencesRepository
    // professionalReferencesRepository;
    // private final EmploymentHistoryRepository employmentHistoryRepository;
    // private final PassportDetailsRepository passportDetailsRepository;
    // private final VisaStatusRepository visaStatusRepository;
    // private final WorkPermitRepository workPermitRepository;
    // private final OtherDetailsRepository otherDetailsRepository;
    // private final RelativeInfoRepository relativeInfoRepository;
    // private final PreviewRepository previewRepository;

    private static final long OTP_EXPIRATION_TIME = 2; // 2 minutes

    @Autowired
    public RegistrationServiceImpl(EmployeeRepository employeeRepository, JwtRepository jwtRepository, JwtUtil jwtUtil,
            EmailService emailService, PasswordEncoder passwordEncoder, RedisTemplate<String, Object> redisTemplate,
            PersonalDetailsRepository personalDetailsRepository, RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.personalDetailsRepository = personalDetailsRepository;
        this.jwtRepository = jwtRepository;
        this.jwtUtil = jwtUtil;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.redisTemplate = redisTemplate;
        this.roleRepository = roleRepository;
    }

    // @Override
    // public Employee createEmployee(EmployeeDTO Employee)
    // {

    // if (!employeeRepository.existsByEmail(Employee.getEmail())) {
    // Employee obj = new Employee();
    // // Create a new instance
    // obj.setFullName(Employee.getFullName());
    // obj.setMobileNumber(Employee.getMobileNumber());
    // obj.setDateOfBirth(Employee.getDateOfBirth());
    // obj.setEmail(Employee.getEmail());
    // obj.setCreatedDate(LocalDateTime.now());
    // if (Employee.getPassword() != null) {
    // obj.setPassword(passwordEncoder.encode(Employee.getPassword()));
    // } else {
    // throw new IllegalArgumentException("Password cannot be null");
    // }
    // obj.setRoleName(Employee.getRoleName());
    // Employee primary = employeeRepository.save(obj);
    // if (primary.getRoleName().equalsIgnoreCase("ADMIN")) {
    // emailService.sendRegistrationEmail(primary.getEmail(),
    // Employee.getPassword(),
    // "Admin Registration Successfully", "registration.html");
    // } else {
    // emailService.sendEmail(primary.getEmail(),
    // primary.getCreatedDate().toString(), "",
    // EmailConstant.SIGN_UP_LINK_SUBJECT,
    // EmailConstant.SIGN_UP_LINK_TEMPLATE_NAME);
    // }
    // return primary;
    // } else {
    // // If the email already exists, throw an exception
    // // throw new
    // NotFoundException(ApplicationConstant.EXISTS_ERROR_RESPONSE_MSG);
    // throw new NotFoundException("Email already exists, kindly use a different
    // one.");
    // }
    // }

    // @Override
    // public Employee saveEmployee(EmployeeDTO Employee) {
    // Optional<Employee> obj =
    // Optional.ofNullable(employeeRepository.findByEmail(Employee.getEmail()));
    // if (obj.isPresent()) {

    // obj.get().setFullName(
    // Employee.getFullName() == null ? obj.get().getFullName() :
    // Employee.getFullName());
    // obj.get().setMobileNumber(Employee.getMobileNumber() == null ?
    // obj.get().getMobileNumber()
    // : Employee.getMobileNumber());
    // obj.get().setDateOfBirth(Employee.getDateOfBirth() == null ?
    // obj.get().getDateOfBirth()
    // : Employee.getDateOfBirth());

    // // Check if password is not null and encrypt it
    // if (Employee.getPassword() != null) {
    // obj.get().setPassword(passwordEncoder.encode(Employee.getPassword()));
    // } else {
    // throw new IllegalArgumentException("Password cannot be null");
    // }

    // obj.get().setRoleName(
    // Employee.getRoleName() == null ? obj.get().getRoleName() :
    // Employee.getRoleName());

    // if (Employee.getPersonalDetailsDTO() != null) {
    // obj.get().setPersonalDetails(addPersonalDetails(Employee.getPersonalDetailsDTO(),
    // obj.get()));
    // } else {
    // obj.get().setPersonalDetails(new PersonalDetails());
    // }
    // if (Employee.getPermanentAddress() != null) {
    // obj.get().setPermanentAddress(addPermanentAddress(Employee.getPermanentAddress(),
    // obj.get()));
    // } else {
    // obj.get().setPermanentAddress(new PermanentAddress());
    // }
    // // Set current address if available

    // obj.get().setAddressDetails(addAddresses(obj.get(),
    // Employee.getAddressDetails()));
    // obj.get().setEducationalQualifications(
    // addEducationalQualifications(Employee.getEducationalQualifications(),
    // obj.get()));
    // obj.get().setDocumentCertificates(addDocumentCertificate(Employee.getDocuments(),
    // obj.get()));
    // obj.get().setEmploymentHistories(addEmploymentHistory(Employee.getEmploymentHistories(),
    // obj.get()));
    // obj.get().setProfessionalReferences(
    // addProfessionalReferences(Employee.getProfessionalReferences(),
    // obj.get()));
    // obj.get().setPassportDetails(addPassportDetails(Employee.getPassportDetails(),
    // obj.get()));
    // obj.get().setVisaStatus(addVisaStatus(Employee.getVisaStatus(),
    // obj.get()));
    // obj.get().setWorkPermit(addWorkPermit(Employee.getWorkPermit(),
    // obj.get()));
    // obj.get().setOtherDetails(addOtherDetails(Employee.getOtherDetails(),
    // obj.get()));
    // Employee primary = employeeRepository.save(obj.get());
    // // emailService.sendEmail(primary.getEmail(),
    // // primary.getCreatedDate().toString(), "",
    // EmailConstant.SIGN_UP_LINK_SUBJECT,
    // // EmailConstant.SIGN_UP_LINK_TEMPLATE_NAME);
    // if (Employee.getCurrentAddresses() != null) {
    // primary.setCurrentAddresses(addCurrentAddresses(Employee.getCurrentAddresses(),
    // obj.get()));
    // } else {
    // primary.setCurrentAddresses(new CurrentAddress()); // Set an empty list for
    // current addresses
    // }
    // primary.setEmployeeRelative(addEmployeeRelative(Employee.getEmployeeRelatives(),
    // obj.get()));
    // return primary;
    // } else {
    // throw new NotFoundException("This email id not register please contact to
    // admin");
    // }
    // }

    // private PersonalDetails addPersonalDetails(PersonalDetailsDTO personalDetail,
    // Employee objs) {
    // Optional<PersonalDetails> personal =
    // personalDetailsRepository.findByEmployee(objs);
    // if (personal.isPresent()) {
    // personalDetailsRepository.delete(personal.get());
    // }
    // PersonalDetails personalDetails = new PersonalDetails();
    // personalDetails.setFirstName(personalDetail.getFirstName());
    // personalDetails.setMiddleName(personalDetail.getMiddleName());
    // personalDetails.setSurname(personalDetail.getSurname());
    // personalDetails.setImageUrl(personalDetail.getImageUrl());
    // personalDetails.setDob(personalDetail.getDob()); // Ensure correct date
    // format
    // personalDetails.setGender(personalDetail.getGender());
    // personalDetails.setMotherName(personalDetail.getMotherName());
    // personalDetails.setFatherName(personalDetail.getFatherName());
    // personalDetails.setEmail(personalDetail.getEmail());
    // personalDetails.setMobile(personalDetail.getMobile());
    // personalDetails.setAadharNumber(personalDetail.getAadharNumber());
    // personalDetails.setAadharUrl(personalDetail.getAadharUrl());
    // personalDetails.setPanNumber(personalDetail.getPanNumber());
    // personalDetails.setPanUrl(personalDetail.getPanUrl());
    // personalDetails.setPassportNumber(personalDetail.getPassportNumber());
    // personalDetails.setPassportUrl(personalDetail.getPassportUrl());

    // personalDetails.setEmployee(objs);
    // return personalDetails;

    // }

    // private PermanentAddress addPermanentAddress(PermanentAddressDTO
    // permanentAddressDTO,
    // Employee Employee) {
    // Optional<PermanentAddress> permanent =
    // permanentAddressRepository.findByEmployee(Employee);
    // if (permanent.isPresent()) {
    // permanentAddressRepository.delete(permanent.get());
    // }
    // PermanentAddress permanentAddress = new PermanentAddress();
    // permanentAddress.setHouseNumber(permanentAddressDTO.getHouseNumber());
    // permanentAddress.setStreetName(permanentAddressDTO.getStreetName());
    // permanentAddress.setTown(permanentAddressDTO.getTown());
    // permanentAddress.setPincode(permanentAddressDTO.getPincode());
    // permanentAddress.setState(permanentAddressDTO.getState());
    // permanentAddress.setCity(permanentAddressDTO.getCity());
    // permanentAddress.setStayFrom(permanentAddressDTO.getStayFrom());
    // permanentAddress.setStayTo(permanentAddressDTO.getStayTo());
    // permanentAddress.setEmergencyContactNumber(permanentAddressDTO.getEmergencyContactNumber());
    // permanentAddress
    // .setEmergencyContactNameAndRelationship(permanentAddressDTO.getEmergencyContactNameAndRelationship());
    // permanentAddress.setEmployee(Employee); // Set the relationship
    // with Employee
    // return permanentAddress;
    // }

    // private CurrentAddress addCurrentAddresses(CurrentAddressDTO dto,
    // Employee Employee) {
    // Optional<CurrentAddress> permanent =
    // currentAddressRepository.findByEmployee(Employee);
    // if (permanent.isPresent()) {
    // currentAddressRepository.delete(permanent.get());
    // }
    // CurrentAddress address = new CurrentAddress();
    // address.setSameAsPermanentAddress(dto.getSameAsPermanentAddress());
    // address.setHouseNumber(dto.getHouseNumber());
    // address.setStreetName(dto.getStreetName());
    // address.setTown(dto.getTown());
    // address.setPincode(dto.getPincode());
    // address.setState(dto.getState());
    // address.setCity(dto.getCity());
    // address.setStayFrom(dto.getStayFrom());
    // address.setStayTo(dto.getStayTo());
    // address.setEmergencyContactNumber(dto.getEmergencyContactNumber());
    // address.setEmergencyContactNameAndRelationship(dto.getEmergencyContactNameAndRelationship());
    // address.setEmployee(Employee); // Associate with primary details
    // return currentAddressRepository.save(address);

    // }

    // private List<AddressDetails> addAddresses(Employee obj,
    // List<AddressDetailsDTO> addressDetails) {
    // List<AddressDetails> permanent =
    // addressDetailsRepository.findByEmployee(obj);
    // if (!permanent.isEmpty()) {
    // addressDetailsRepository.deleteAll(permanent);
    // }
    // List<AddressDetails> list = new ArrayList<>();
    // for (AddressDetailsDTO lists : addressDetails) {
    // AddressDetails address = new AddressDetails();
    // address.setPincode(lists.getPincode());
    // address.setAddressLine(lists.getAddressLine());
    // address.setCountry(lists.getCountry());
    // address.setStayFrom(lists.getStayFrom());
    // address.setContactNumberWithRelationship(lists.getContactNumberWithRelationship());
    // address.setStayTo(lists.getStayTo());
    // address.setEmployee(obj);
    // list.add(address);
    // }
    // return list;
    // }

    // private List<EducationalQualification> addEducationalQualifications(
    // List<EducationalQualificationDTO> educationalQualificationsDTO,
    // Employee Employee) {
    // List<EducationalQualification> educationalQualifications = new ArrayList<>();
    // List<EducationalQualification> education = educationalQualificationRepository
    // .findAllByEmployee(Employee);
    // if (!education.isEmpty()) {
    // educationalQualificationRepository.deleteAll(education);
    // }
    // for (EducationalQualificationDTO eduDTO : educationalQualificationsDTO) {
    // EducationalQualification edu = new EducationalQualification();
    // edu.setDegreeName(eduDTO.getDegreeName());
    // edu.setSubject(eduDTO.getSubject());
    // edu.setPassingYear(eduDTO.getPassingYear());
    // edu.setRollNumber(eduDTO.getRollNumber());
    // edu.setGradeOrPercentage(eduDTO.getGradeOrPercentage());
    // edu.setEmployee(Employee);
    // educationalQualifications.add(edu);
    // }
    // return educationalQualifications;
    // }

    // private List<DocumentCertificates>
    // addDocumentCertificate(List<DocumentCertificateDTO> documents,
    // Employee obj) {
    // List<DocumentCertificates> documentCertificates = new ArrayList<>();
    // List<DocumentCertificates> doc =
    // documentCertificatesRepository.findAllByEmployee(obj);
    // if (!doc.isEmpty()) {
    // documentCertificatesRepository.deleteAll(doc);
    // }
    // for (DocumentCertificateDTO dto : documents) {
    // DocumentCertificates document = new DocumentCertificates();
    // document.setDocumentType(dto.getDocumentType());
    // document.setDegreeCertificate(dto.getDegreeCertificate());
    // document.setDegreeCertificateUrl(dto.getDegreeCertificateUrl());
    // document.setPassingCertificate(dto.getPassingCertificate());
    // document.setPassingCertificateUrl(dto.getPassingCertificateUrl());
    // document.setEmployee(obj);
    // documentCertificates.add(document);
    // }
    // return documentCertificates;
    // }

    // private List<EmploymentHistory>
    // addEmploymentHistory(List<EmploymentHistoryDTO> employmentHistoryDTO,
    // Employee Employee) {
    // List<EmploymentHistory> doc =
    // employmentHistoryRepository.findAllByEmployee(Employee);
    // if (!doc.isEmpty()) {
    // employmentHistoryRepository.deleteAll(doc);
    // }
    // List<EmploymentHistory> employmentHistories = new ArrayList<>();
    // for (EmploymentHistoryDTO empDTO : employmentHistoryDTO) {
    // EmploymentHistory employmentHistory = new EmploymentHistory();
    // employmentHistory.setPreviousEmployerName(empDTO.getPreviousEmployerName());
    // employmentHistory.setEmployerAddress(empDTO.getEmployerAddress());
    // employmentHistory.setTelephoneNumber(empDTO.getTelephoneNumber());
    // employmentHistory.setEmployeeCode(empDTO.getEmployeeCode());
    // employmentHistory.setDesignation(empDTO.getDesignation());
    // employmentHistory.setDepartment(empDTO.getDepartment());
    // employmentHistory.setManagerName(empDTO.getManagerName());
    // employmentHistory.setManagerEmail(empDTO.getManagerEmail());
    // employmentHistory.setManagerContactNo(empDTO.getManagerContactNo());
    // employmentHistory.setReasonsForLeaving(empDTO.getReasonsForLeaving());
    // employmentHistory.setEmploymentStartDate(empDTO.getEmploymentStartDate());
    // employmentHistory.setEmploymentEndDate(empDTO.getEmploymentEndDate());
    // employmentHistory.setEmploymentType(empDTO.getEmploymentType());
    // employmentHistory.setExperienceCertificateUrl(empDTO.getExperienceCertificateUrl());
    // employmentHistory.setRelievingLetterUrl(empDTO.getRelievingLetterUrl());
    // employmentHistory.setLastMonthSalarySlipUrl(empDTO.getLastMonthSalarySlipUrl());
    // employmentHistory.setAppointmentLetterUrl(empDTO.getAppointmentLetterUrl());
    // employmentHistory.setEmployee(Employee);
    // employmentHistories.add(employmentHistory);
    // }
    // return employmentHistories;
    // }

    // private List<ProfessionalReferences>
    // addProfessionalReferences(List<ProfessionalReferencesDTO> referencesDTOs,
    // Employee Employee) {
    // List<ProfessionalReferences> professionalReferences = new ArrayList<>();
    // List<ProfessionalReferences> professionalRef =
    // professionalReferencesRepository
    // .findAllByEmployee(Employee);
    // if (!professionalRef.isEmpty()) {
    // professionalReferencesRepository.deleteAll(professionalRef);
    // }
    // for (ProfessionalReferencesDTO referenceDTO : referencesDTOs) {
    // ProfessionalReferences reference = new ProfessionalReferences();
    // reference.setProfessionalId(referenceDTO.getProfessionalId()); // Assuming
    // you want to set this
    // reference.setName(referenceDTO.getName());
    // reference.setDesignation(referenceDTO.getDesignation());
    // reference.setEmail(referenceDTO.getEmail());
    // reference.setContactNumber(referenceDTO.getContactNumber());
    // reference.setEmployee(Employee); // Associate with primary
    // details
    // professionalReferences.add(reference);
    // }
    // return professionalReferences;
    // }

    // private List<RelativeInfo> addRelativeInfo(List<RelativeInfoDTO>
    // relativeInfoDTOs,
    // EmployeeRelative employeeRelative) {
    // List<RelativeInfo> relativeInfoList = new ArrayList<>();
    // List<RelativeInfo> relativeIn =
    // relativeInfoRepository.findAllByEmployeeRelative(employeeRelative);
    // if (!relativeIn.isEmpty()) {
    // relativeInfoRepository.deleteAll(relativeIn);
    // }
    // for (RelativeInfoDTO relativeInfoDTO : relativeInfoDTOs) {
    // RelativeInfo relativeInfo = new RelativeInfo();
    // relativeInfo.setName(relativeInfoDTO.getName());
    // relativeInfo.setEmployeeId(relativeInfoDTO.getEmployeeId());
    // relativeInfo.setRelationship(relativeInfoDTO.getRelationship());
    // relativeInfo.setDepartment(relativeInfoDTO.getDepartment());
    // relativeInfo.setLocation(relativeInfoDTO.getLocation());
    // relativeInfo.setRemarks(relativeInfoDTO.getRemarks());
    // relativeInfo.setEmployeeRelative(employeeRelative); // Set the relationship
    // with Employee

    // relativeInfoList.add(relativeInfo);
    // }

    // return relativeInfoRepository.saveAll(relativeInfoList);
    // }

    // private PassportDetails addPassportDetails(PassportDetailsDTO
    // passportDetailsDTO, Employee Employee) {
    // Optional<PassportDetails> permanent =
    // passportDetailsRepository.findByEmployee(Employee);
    // permanent.ifPresent(passportDetailsRepository::delete);

    // PassportDetails passportDetails = new PassportDetails();
    // passportDetails.setPassportNumber(passportDetailsDTO.getPassportNumber());
    // passportDetails.setIssueDate(passportDetailsDTO.getIssueDate()); // Adjusted
    // field name
    // passportDetails.setPlaceOfIssue(passportDetailsDTO.getPlaceOfIssue());
    // passportDetails.setExpiryDate(passportDetailsDTO.getExpiryDate());
    // passportDetails.setCountryOfIssue(passportDetailsDTO.getCountryOfIssue());
    // passportDetails.setNationality(passportDetailsDTO.getNationality());
    // passportDetails.setEmployee(Employee); // Associate with primary
    // details
    // return passportDetails;
    // }

    // private EmployeeRelative addEmployeeRelative(EmployeeRelativeDTO
    // employeeRelativeDTO,
    // Employee Employee) {
    // Optional<EmployeeRelative> permanent =
    // employeeRelativeRepository.findByEmployee(Employee);
    // if (permanent.isPresent()) {
    // employeeRelativeRepository.delete(permanent.get());
    // }
    // EmployeeRelative employeeRelative = new EmployeeRelative();
    // employeeRelative.setHasRelative(employeeRelativeDTO.getHasRelative());
    // employeeRelative.setEmployee(Employee);
    // EmployeeRelative employee =
    // employeeRelativeRepository.save(employeeRelative);
    // employeeRelative.setRelativeInfoList(addRelativeInfo(employeeRelativeDTO.getRelativeInfoDTOS(),
    // employee));
    // return employee;
    // }

    // private VisaStatus addVisaStatus(VisaStatusDTO visaStatusDTO, Employee
    // Employee) {
    // Optional<VisaStatus> permanent =
    // visaStatusRepository.findByEmployee(Employee);
    // if (permanent.isPresent()) {
    // visaStatusRepository.delete(permanent.get());
    // }
    // VisaStatus visaStatus = new VisaStatus();
    // visaStatus.setCitizen(visaStatusDTO.getCitizen()); // Fixed method call
    // visaStatus.setExpatOnGreenCard(visaStatusDTO.getExpatOnGreenCard());
    // visaStatus.setExpatOnWorkPermit(visaStatusDTO.getExpatOnWorkPermit());
    // visaStatus.setExpatOnPermanentResidencyPermit(visaStatusDTO.getExpatOnPermanentResidencyPermit());
    // visaStatus.setAnyOtherStatus(visaStatusDTO.getAnyOtherStatus());
    // visaStatus.setEmployee(Employee);
    // return visaStatus;
    // }

    // private WorkPermit addWorkPermit(WorkPermitDTO workPermitDTO, Employee
    // Employee) {
    // Optional<WorkPermit> permanent =
    // workPermitRepository.findByEmployee(Employee);
    // if (permanent.isPresent()) {
    // workPermitRepository.delete(permanent.get());
    // }
    // WorkPermit workPermit = new WorkPermit();
    // workPermit.setLegalRightToWork(workPermitDTO.getLegalRightToWork());
    // workPermit.setWorkPermitDetails(workPermitDTO.getWorkPermitDetails());
    // workPermit.setWorkPermitValidTill(workPermitDTO.getWorkPermitValidTill());
    // workPermit.setPassportCopy(workPermitDTO.getPassportCopy());
    // workPermit.setPassportCopyPath(workPermitDTO.getPassportCopyPath());
    // workPermit.setEmployee(Employee); // Associate with primary
    // details

    // return workPermit;
    // }

    // private OtherDetails addOtherDetails(OtherDetailsDTO otherDetailsDTO,
    // Employee Employee) {
    // Optional<OtherDetails> permanent =
    // otherDetailsRepository.findByEmployee(Employee);
    // if (permanent.isPresent()) {
    // otherDetailsRepository.delete(permanent.get());
    // }
    // OtherDetails otherDetails = new OtherDetails();
    // otherDetails.setOtherId(otherDetailsDTO.getOtherId());
    // otherDetails.setIllness(otherDetailsDTO.getIllness());
    // otherDetails.setSelfIntroduction(otherDetailsDTO.getSelfIntroduction());
    // otherDetails.setDeclarationAccepted(otherDetailsDTO.getDeclarationAccepted());
    // otherDetails.setEmployee(Employee);

    // return otherDetails;
    // }

    // private Preview addPreview(PreviewDTO previewDTO, Employee
    // Employee) {
    // Optional<Preview> permanent =
    // previewRepository.findByEmployee(Employee);
    // if (permanent.isPresent()) {
    // previewRepository.delete(permanent.get());
    // }

    // Preview preview = new Preview();
    // preview.setEmployeeSignatureUrl(previewDTO.getEmployeeSignatureUrl());
    // preview.setSignatureDate(previewDTO.getSignatureDate());
    // preview.setPlace(previewDTO.getPlace());
    // preview.setEmployee(Employee); // Associate with primary details
    // return preview;
    // }

    // @Override
    // public List<Employee> getAllEmployee() {
    // List<Employee> Employee = employeeRepository.findAll();
    // return Employee;

    // }

    // @Override
    // public Employee getEmployeeById(Long primaryId) {
    // Employee primaryDetail =
    // employeeRepository.findById(primaryId).orElse(null);
    // return primaryDetail;
    // }

    // @Override
    // public Employee updateEmployeeById(Long primaryId, Employee
    // Employee) {
    // Optional<Employee> existingEmployee =
    // employeeRepository.findById(primaryId);
    // if (existingEmployee.isPresent()) {
    // existingEmployee.get().setFullName(Employee.getFullName());
    // existingEmployee.get().setMobileNumber(Employee.getMobileNumber());
    // existingEmployee.get().setDateOfBirth(Employee.getDateOfBirth());
    // existingEmployee.get().setEmail(Employee.getEmail());
    // existingEmployee.get().setPassword(Employee.getPassword());
    // existingEmployee.get().setCreatedDate(LocalDateTime.now());
    // employeeRepository.save(existingEmployee.get());
    // return existingEmployee.get();
    // } else {
    // throw new NotFoundException("Record not available");
    // }
    // }

    // @Override
    // public Employee deleteEmployeeById(Long primaryId) {
    // Employee EmployeeDelete =
    // employeeRepository.findById(primaryId).orElse(null);
    // if (EmployeeDelete != null) {
    // employeeRepository.delete(EmployeeDelete);
    // return EmployeeDelete;
    // } else {
    // throw new RuntimeException("Employee not found with id: " + primaryId);
    // }

    // }

    // @Override
    // public String resendOtp(String email) {
    // // Check if an OTP exists in Redis
    // String cachedOtp = (String) redisTemplate.opsForValue().get(email);

    // if (cachedOtp != null) {
    // // If OTP exists, send the same OTP again
    // try {
    // emailService.sendEmail(email, cachedOtp, EmailConstant.SIGN_UP_OTP_SUBJECT,
    // EmailConstant.SIGN_UP_OTP_TEMPLATE_NAME);
    // return cachedOtp; // Return the existing OTP
    // } catch (Exception e) {
    // // Handle email sending error
    // throw new RuntimeException("Failed to send OTP email. Please try again
    // later.");
    // }
    // } else {
    // // Generate a new OTP if it does not exist or has expired
    // return sendOtpEmail(email);
    // }
    // }

    // @Override
    // public Employee findByEmail(String email) {

    // return employeeRepository.findByEmail(email);
    // }

    // @Override
    // public void createUser(Employee user) {
    // // Log the user details before saving
    // System.out.println("Saving User: " + user);

    // // Encode the password
    // user.setPassword(passwordEncoder.encode(user.getPassword()));
    // employeeRepository.save(user); // Save the user to the database
    // }

    public RegistrationResponseDTO registerEmployee(RegistrationRequestDTO employeeRegistrationDTO) {

        if (personalDetailsRepository.existsByPersonalEmail(employeeRegistrationDTO.getEmail()))
            throw new AlreadyExistsException("Email already exists, kindly use a different one.");

        if(employeeRepository.findByMobileNumber(employeeRegistrationDTO.getMobileNumber()).isPresent())
            throw new MobileNumberAlreadyExistsException("Employee with this mobile number already exists, kindly use a different one.");    
        
        System.out.println("1111111111111");
        Employee employee = new Employee();

        String fullName = employeeRegistrationDTO.getFullName();

        String[] parts = fullName.trim().split("\\s+");

        if (parts.length == 1) {
            employee.setFirstName(parts[0]);
        } else if (parts.length == 2) {
            employee.setFirstName(parts[0]);
            employee.setLastName(parts[1]);
        } else {
            employee.setFirstName(parts[0]);
            employee.setLastName(parts[parts.length - 1]);
            employee.setMiddleName(String.join(" ", Arrays.copyOfRange(parts, 1, parts.length - 1)));
        }

        employee.setEmail(employeeRegistrationDTO.getEmail());
        System.out.println(employeeRegistrationDTO.toString());
        employee.setMobileNumber(employeeRegistrationDTO.getMobileNumber());
        employee.setDateOfBirth(employeeRegistrationDTO.getDateOfBirth());
        employee.setStatus("PENDING");
        String currentTimeStampString   = LocalDateTime.now().toString();
        employee.setCreatedTimeStamp(currentTimeStampString);

        PersonalDetails personalDetails = new PersonalDetails();
        personalDetails.setPersonalEmail(employee.getEmail());

        employee.setPersonalDetails(personalDetails);
        personalDetails.setEmployee(employee);

        EmployeeReg employeeReg = new EmployeeReg();
        employeeReg.setEmail(employeeRegistrationDTO.getEmail());
        employeeReg.setPassword("temporary password");

        Role role = roleRepository.findByRoleName("EMPLOYEE");
        employeeReg.setRole(role);

        employee.setEmployeeReg(employeeReg);
        employeeReg.setEmployee(employee);

        employeeRepository.save(employee);

        System.out.println("22222222222222");

        // String token = UUID.randomUUID().toString();
        String tokenString = employeeRegistrationDTO.getEmail() + "|" + employeeRegistrationDTO.getMobileNumber() + "|"
                + currentTimeStampString;
        String encryptedToken = null;
        try {
            encryptedToken = EncryptionUtil.encrypt(tokenString);
        } catch (Exception e) {
            throw new EncryptionException("Error encrypting token: " + e.getMessage());
        }

        emailService.sendEmail(employee.getEmail(), encryptedToken,
                EmailConstant.SIGN_UP_LINK_SUBJECT, EmailConstant.SIGN_UP_LINK_TEMPLATE_NAME);

        return new RegistrationResponseDTO(employeeRegistrationDTO.getEmail(),
                employeeRegistrationDTO.getFullName(), employeeRegistrationDTO.getMobileNumber(),
                employeeRegistrationDTO.getDateOfBirth(), "PENDING");
    }

    @Override
    public String sendOtpEmail(String email) {
        // Generate a random 6 digit number for OTP
        Random random = new Random();
        String otp = String.valueOf(100000 + random.nextInt(900000));

        // Store OTP in Redis with 2-minute expiration
        redisTemplate.opsForValue().set(email, otp, OTP_EXPIRATION_TIME,
                TimeUnit.MINUTES);

        System.out.println("ooooooooooooooooooooooooooooooooooooooo" + redisTemplate.opsForValue().get(email));

        emailService.sendEmail(email, otp, EmailConstant.SIGN_UP_OTP_SUBJECT,
                EmailConstant.SIGN_UP_OTP_TEMPLATE_NAME);

        return otp; // Return the OTP
    }

    public ValidateOtpDto validateOtp(String token, String otp) {
        String decryptedToken;
        try {
            decryptedToken = EncryptionUtil.decrypt(token);
        } catch (Exception e) {
            throw new EncryptionException("Error decrypting token: " + e.getMessage());
        }

        System.out.println(token+" "+decryptedToken);
        // Split the decrypted token to get email and mobile number
        String[] parts = decryptedToken.split("\\|");

        String email = parts[0];

        String cachedOtp = (String) redisTemplate.opsForValue().get(email);
        System.out.println("Retrieved OTP from Redis for " + email + ": " + cachedOtp + " " + otp);

        if (cachedOtp != null && cachedOtp.equals(otp)) {
            System.out.println("jkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" + redisTemplate.opsForValue().get(email));
            redisTemplate.delete(email);
            System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjj" + redisTemplate.opsForValue().get(email));

            return new ValidateOtpDto(email, true);
        }
        return new ValidateOtpDto(email, false);
    }

    @Override
    public String resendActivationLink(String email) {

        Employee employee = employeeRepository.findByEmail(email);
        String currentTimeStampString   = LocalDateTime.now().toString();

        // String token = UUID.randomUUID().toString();
        String tokenString = email + "|" + employee.getMobileNumber() + "|"
                + currentTimeStampString;
        String encryptedToken = null;
        try {
            encryptedToken = EncryptionUtil.encrypt(tokenString);
        } catch (Exception e) {
            throw new EncryptionException("Error encrypting token: " + e.getMessage());
        }

        // Generate a new activation link with a unique identifier or timestamp
        String activationLink = EmailConstant.ACTIVE_SIGNUP_LINK + "?token=" + encryptedToken;

        // Sending the email with the activation link
        emailService.sendEmail(email, encryptedToken, EmailConstant.RESEND_LINK_SUBJECT,
                EmailConstant.SIGN_UP_LINK_TEMPLATE_NAME);

        return activationLink; // Return the new activation link or a success message
    }

    @Override
    public ValidateTokenResponseDto validateToken(String token) {
        // Decrypt the token
        String decryptedToken;
        try {
            decryptedToken = EncryptionUtil.decrypt(token);
        } catch (Exception e) {
            throw new EncryptionException("Error decrypting token: " + e.getMessage());
        }

        System.out.println(token+" "+decryptedToken);
        // Split the decrypted token to get email and mobile number
        String[] parts = decryptedToken.split("\\|");

        String email = parts[0];
        String mobileNumber = parts[1];
        String timeStamp = parts[2];

        if (parts.length != 3) {
            return new ValidateTokenResponseDto(false, "Invalid token", email); // Invalid token format
        }

        Employee employee = employeeRepository.findByEmail(email);

        if(employee==null){
            return new ValidateTokenResponseDto(false, "Invalid token", email);
        }

        String employeeMobileNumber = employee.getMobileNumber();
        String employeeCreatedTimeStamp = employee.getCreatedTimeStamp();

        if(!mobileNumber.equals(employeeMobileNumber) || !timeStamp.equals(employeeCreatedTimeStamp)) {
            return new ValidateTokenResponseDto(false, "Invalid token", email); // Invalid token format
        }

        System.out.println("hereeeeeeeeeeeeeeeeeeeeeeeee");
        LocalDateTime employeeDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
        LocalDateTime dateTime = LocalDateTime.parse(timeStamp, formatter);

        Duration duration = Duration.between(dateTime, employeeDateTime);
        long days = duration.toDays();
        if(days > 1) {
            return new ValidateTokenResponseDto(false, "Token expired", email); // Token expired
        }

        // Check if the email and mobile number match the expected values
        return new ValidateTokenResponseDto(true, "Valid token", email);
    }

    // @Override
    // public PrimaryPreviewResponse getEmployee(Long primaryId) {
    // Employee primaryDetail = employeeRepository.findById(primaryId).orElse(null);
    // PrimaryPreviewResponse primaryPreviewResponse = new PrimaryPreviewResponse();
    // primaryPreviewResponse.setPrimaryId(primaryDetail.getPrimaryId());
    // primaryPreviewResponse.setEmail(primaryDetail.getEmail());
    // primaryPreviewResponse.setFullName(primaryDetail.getFullName());
    // primaryPreviewResponse.setDateOfBirth(primaryDetail.getDateOfBirth());
    // primaryPreviewResponse.setMobileNumber(primaryDetail.getMobileNumber());
    // primaryPreviewResponse.setCreatedDate(primaryDetail.getCreatedDate());
    // primaryPreviewResponse.setAddressDetails(primaryDetail.getAddressDetails());
    // primaryPreviewResponse.setPermanentAddress(primaryDetail.getPermanentAddress());
    // primaryPreviewResponse.setCurrentAddresses(primaryDetail.getCurrentAddresses());
    // primaryPreviewResponse.setDocuments(primaryDetail.getDocumentCertificates());
    // primaryPreviewResponse.setEducationalQualifications(primaryDetail.getEducationalQualifications());
    // primaryPreviewResponse.setEmploymentHistories(primaryDetail.getEmploymentHistories());
    // primaryPreviewResponse.setProfessionalReferences(primaryDetail.getProfessionalReferences());
    // primaryPreviewResponse.setPassportDetails(primaryDetail.getPassportDetails());
    // primaryPreviewResponse.setVisaStatus(primaryDetail.getVisaStatus());
    // primaryPreviewResponse.setWorkPermit(primaryDetail.getWorkPermit());
    // primaryPreviewResponse.setEmployeeRelatives(primaryDetail.getEmployeeRelative());
    // primaryPreviewResponse.setOtherDetails(primaryDetail.getOtherDetails());
    // primaryPreviewResponse.setPersonalDetails(primaryDetail.getPersonalDetails());
    // primaryPreviewResponse.setRoleName(primaryDetail.getRoleName());
    // return primaryPreviewResponse;
    // }

    // @Override
    // public String sendPreviewDetailsToHR(PreviewDetailsDTO previewDetailsDTO) {

    // List<Employee> Employee = employeeRepository.findByRoleName("HR");
    // if (!Employee.isEmpty()) {
    // for (Employee hrEmail : Employee) {
    // emailService.sendPreviewEmailToHr(
    // hrEmail.getEmail(),
    // previewDetailsDTO.getEmployeeSignatureUrl(),
    // previewDetailsDTO.getSignatureDate(),
    // previewDetailsDTO.getPlace(),
    // EmailConstant.PREVIEW_DETAILS_SUBJECT,
    // EmailConstant.PREVIEW_DETAILS_TEMPLATE_NAME,
    // EmailConstant.PREVIEW_DETAILS_LINK + previewDetailsDTO.getPrimaryId());
    // }
    // }
    // return "Send Email Successfully ";

    // }
}
