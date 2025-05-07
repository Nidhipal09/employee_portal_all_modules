// package com.employeeportal.serviceImpl;

// import java.time.LocalDateTime;
// import java.util.*;
// import java.util.concurrent.TimeUnit;

// import com.employeeportal.exception.NotFoundException;
// import com.employeeportal.model.*;
// import com.employeeportal.model.dto.*;
// import com.employeeportal.repository.*;
// import com.employeeportal.util.JwtUtil;
// import io.jsonwebtoken.Claims;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;
// import com.employeeportal.config.EmailConstant;
// import com.employeeportal.service.EmailService;
// import com.employeeportal.service.PrimaryDetailsService;

// @Service
// public class PrimaryDetailsServiceImpl implements PrimaryDetailsService {

//     private final PrimaryDetailsRepository primaryDetailsRepo;

//     private final JwtRepository jwtRepository;
//     private final JwtUtil jwtUtil;

//     private final EmailService emailService;
//     private final EmployeeRelativeRepository employeeRelativeRepository;
//     private final PersonalDetailsRepository personalDetailsRepository;
//     private final PermanentAddressRepository permanentAddressRepository;
//     private final CurrentAddressRepository currentAddressRepository;
//     private final AddressDetailsRepository addressDetailsRepository;
//     private final PasswordEncoder passwordEncoder;
//     //private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//     private final RedisTemplate<String, Object> redisTemplate;
//     private final DocumentCertificatesRepository documentCertificatesRepository;
//     private final EducationalQualificationRepository educationalQualificationRepository;
//     private final ProfessionalReferencesRepository professionalReferencesRepository;
//     private final EmploymentHistoryRepository employmentHistoryRepository;
//     private final PassportDetailsRepository passportDetailsRepository;
//     private final VisaStatusRepository visaStatusRepository;
//     private final WorkPermitRepository workPermitRepository;
//     private final OtherDetailsRepository otherDetailsRepository;
//     private final RelativeInfoRepository relativeInfoRepository;
//     private final PreviewRepository previewRepository;

//     private static final long OTP_EXPIRATION_TIME = 2; // 2 minutes

//     @Autowired
//     public PrimaryDetailsServiceImpl(PrimaryDetailsRepository primaryDetailsRepo, JwtRepository jwtRepository, JwtUtil jwtUtil, EmailService emailService, EmployeeRelativeRepository employeeRelativeRepository, PersonalDetailsRepository personalDetailsRepository, PermanentAddressRepository permanentAddressRepository, CurrentAddressRepository currentAddressRepository, AddressDetailsRepository addressDetailsRepository, PasswordEncoder passwordEncoder, RedisTemplate<String, Object> redisTemplate, DocumentCertificatesRepository documentCertificatesRepository, EducationalQualificationRepository educationalQualificationRepository, ProfessionalReferencesRepository professionalReferencesRepository, EmploymentHistoryRepository employmentHistoryRepository, PassportDetailsRepository passportDetailsRepository, VisaStatusRepository visaStatusRepository, WorkPermitRepository workPermitRepository, OtherDetailsRepository otherDetailsRepository, RelativeInfoRepository relativeInfoRepository, PreviewRepository previewRepository) {
//         this.primaryDetailsRepo = primaryDetailsRepo;
//         this.jwtRepository = jwtRepository;
//         this.jwtUtil = jwtUtil;
//         this.emailService = emailService;
//         this.employeeRelativeRepository = employeeRelativeRepository;
//         this.personalDetailsRepository = personalDetailsRepository;
//         this.permanentAddressRepository = permanentAddressRepository;
//         this.currentAddressRepository = currentAddressRepository;
//         this.addressDetailsRepository = addressDetailsRepository;
//         this.passwordEncoder = passwordEncoder;
//         this.redisTemplate = redisTemplate;
//         this.documentCertificatesRepository = documentCertificatesRepository;
//         this.educationalQualificationRepository = educationalQualificationRepository;
//         this.professionalReferencesRepository = professionalReferencesRepository;
//         this.employmentHistoryRepository = employmentHistoryRepository;
//         this.passportDetailsRepository = passportDetailsRepository;
//         this.visaStatusRepository = visaStatusRepository;
//         this.workPermitRepository = workPermitRepository;
//         this.otherDetailsRepository = otherDetailsRepository;
//         this.relativeInfoRepository = relativeInfoRepository;
//         this.previewRepository = previewRepository;
//     }


//     @Override
//     public PrimaryDetails createPrimaryDetails(PrimaryDetailsDTO primaryDetails) {

//         if (!primaryDetailsRepo.existsByEmail(primaryDetails.getEmail())) {
//             PrimaryDetails obj = new PrimaryDetails();
//             // Create a new instance
//             obj.setFullName(primaryDetails.getFullName());
//             obj.setMobileNumber(primaryDetails.getMobileNumber());
//             obj.setDateOfBirth(primaryDetails.getDateOfBirth());
//             obj.setEmail(primaryDetails.getEmail());
//             obj.setCreatedDate(LocalDateTime.now());
//             if (primaryDetails.getPassword() != null) {
//                 obj.setPassword(passwordEncoder.encode(primaryDetails.getPassword()));
//             } else {
//                 throw new IllegalArgumentException("Password cannot be null");
//             }
//             obj.setRoleName(primaryDetails.getRoleName());
//             PrimaryDetails primary = primaryDetailsRepo.save(obj);
//             if (primary.getRoleName().equalsIgnoreCase("ADMIN")) {
//                 emailService.sendRegistrationEmail(primary.getEmail(), primaryDetails.getPassword(), "Admin Registration Successfully", "registration.html");
//             } else {
//                 emailService.sendEmail(primary.getEmail(), primary.getCreatedDate().toString(), "", EmailConstant.SIGN_UP_LINK_SUBJECT, EmailConstant.SIGN_UP_LINK_TEMPLATE_NAME);
//             }
//             return primary;
//         } else {
//             // If the email already exists, throw an exception
//             //throw new NotFoundException(ApplicationConstant.EXISTS_ERROR_RESPONSE_MSG);
//             throw new NotFoundException("Email already exists, kindly use a different one.");
//         }
//     }

//     @Override
//     public PrimaryDetails savePrimaryDetails(PrimaryDetailsDTO primaryDetails) {
//         Optional<PrimaryDetails> obj = Optional.ofNullable(primaryDetailsRepo.findByEmail(primaryDetails.getEmail()));
//         if (obj.isPresent()) {

//             obj.get().setFullName(primaryDetails.getFullName() == null ? obj.get().getFullName() : primaryDetails.getFullName());
//             obj.get().setMobileNumber(primaryDetails.getMobileNumber() == null ? obj.get().getMobileNumber() : primaryDetails.getMobileNumber());
//             obj.get().setDateOfBirth(primaryDetails.getDateOfBirth() == null ? obj.get().getDateOfBirth() : primaryDetails.getDateOfBirth());

//             // Check if password is not null and encrypt it
//             if (primaryDetails.getPassword() != null) {
//                 obj.get().setPassword(passwordEncoder.encode(primaryDetails.getPassword()));
//             } else {
//                 throw new IllegalArgumentException("Password cannot be null");
//             }

//             obj.get().setRoleName(primaryDetails.getRoleName() == null ? obj.get().getRoleName() : primaryDetails.getRoleName());

//             if (primaryDetails.getPersonalDetailsDTO() != null) {
//                 obj.get().setPersonalDetails(addPersonalDetails(primaryDetails.getPersonalDetailsDTO(), obj.get()));
//             } else {
//                 obj.get().setPersonalDetails(new PersonalDetails());
//             }
//             if (primaryDetails.getPermanentAddress() != null) {
//                 obj.get().setPermanentAddress(addPermanentAddress(primaryDetails.getPermanentAddress(), obj.get()));
//             } else {
//                 obj.get().setPermanentAddress(new PermanentAddress());
//             }
//             // Set current address if available

//             obj.get().setAddressDetails(addAddresses(obj.get(), primaryDetails.getAddressDetails()));
//             obj.get().setEducationalQualifications(addEducationalQualifications(primaryDetails.getEducationalQualifications(), obj.get()));
//             obj.get().setDocumentCertificates(addDocumentCertificate(primaryDetails.getDocuments(), obj.get()));
//             obj.get().setEmploymentHistories(addEmploymentHistory(primaryDetails.getEmploymentHistories(), obj.get()));
//             obj.get().setProfessionalReferences(addProfessionalReferences(primaryDetails.getProfessionalReferences(), obj.get()));
//             obj.get().setPassportDetails(addPassportDetails(primaryDetails.getPassportDetails(), obj.get()));
//             obj.get().setVisaStatus(addVisaStatus(primaryDetails.getVisaStatus(), obj.get()));
//             obj.get().setWorkPermit(addWorkPermit(primaryDetails.getWorkPermit(), obj.get()));
//             obj.get().setOtherDetails(addOtherDetails(primaryDetails.getOtherDetails(), obj.get()));
//             PrimaryDetails primary = primaryDetailsRepo.save(obj.get());
// //            emailService.sendEmail(primary.getEmail(), primary.getCreatedDate().toString(), "", EmailConstant.SIGN_UP_LINK_SUBJECT, EmailConstant.SIGN_UP_LINK_TEMPLATE_NAME);
//             if (primaryDetails.getCurrentAddresses() != null) {
//                 primary.setCurrentAddresses(addCurrentAddresses(primaryDetails.getCurrentAddresses(), obj.get()));
//             } else {
//                 primary.setCurrentAddresses(new CurrentAddress());  // Set an empty list for current addresses
//             }
//             primary.setEmployeeRelative(addEmployeeRelative(primaryDetails.getEmployeeRelatives(), obj.get()));
//             return primary;
//         } else {
//             throw new NotFoundException("This email id not register please contact to admin");
//         }
//     }


//     private PersonalDetails addPersonalDetails(PersonalDetailsDTO personalDetail, PrimaryDetails objs) {
//         Optional<PersonalDetails> personal = personalDetailsRepository.findByPrimaryDetails(objs);
//         if (personal.isPresent()) {
//             personalDetailsRepository.delete(personal.get());
//         }
//         PersonalDetails personalDetails = new PersonalDetails();
//         personalDetails.setFirstName(personalDetail.getFirstName());
//         personalDetails.setMiddleName(personalDetail.getMiddleName());
//         personalDetails.setSurname(personalDetail.getSurname());
//         personalDetails.setImageUrl(personalDetail.getImageUrl());
//         personalDetails.setDob(personalDetail.getDob());  // Ensure correct date format
//         personalDetails.setGender(personalDetail.getGender());
//         personalDetails.setMotherName(personalDetail.getMotherName());
//         personalDetails.setFatherName(personalDetail.getFatherName());
//         personalDetails.setEmail(personalDetail.getEmail());
//         personalDetails.setMobile(personalDetail.getMobile());
//         personalDetails.setAadharNumber(personalDetail.getAadharNumber());
//         personalDetails.setAadharUrl(personalDetail.getAadharUrl());
//         personalDetails.setPanNumber(personalDetail.getPanNumber());
//         personalDetails.setPanUrl(personalDetail.getPanUrl());
//         personalDetails.setPassportNumber(personalDetail.getPassportNumber());
//         personalDetails.setPassportUrl(personalDetail.getPassportUrl());

//         personalDetails.setPrimaryDetails(objs);
//         return personalDetails;

//     }

//     private PermanentAddress addPermanentAddress(PermanentAddressDTO permanentAddressDTO, PrimaryDetails primaryDetails) {
//         Optional<PermanentAddress> permanent = permanentAddressRepository.findByPrimaryDetails(primaryDetails);
//         if (permanent.isPresent()) {
//             permanentAddressRepository.delete(permanent.get());
//         }
//         PermanentAddress permanentAddress = new PermanentAddress();
//         permanentAddress.setHouseNumber(permanentAddressDTO.getHouseNumber());
//         permanentAddress.setStreetName(permanentAddressDTO.getStreetName());
//         permanentAddress.setTown(permanentAddressDTO.getTown());
//         permanentAddress.setPincode(permanentAddressDTO.getPincode());
//         permanentAddress.setState(permanentAddressDTO.getState());
//         permanentAddress.setCity(permanentAddressDTO.getCity());
//         permanentAddress.setStayFrom(permanentAddressDTO.getStayFrom());
//         permanentAddress.setStayTo(permanentAddressDTO.getStayTo());
//         permanentAddress.setEmergencyContactNumber(permanentAddressDTO.getEmergencyContactNumber());
//         permanentAddress.setEmergencyContactNameAndRelationship(permanentAddressDTO.getEmergencyContactNameAndRelationship());
//         permanentAddress.setPrimaryDetails(primaryDetails); // Set the relationship with PrimaryDetails
//         return permanentAddress;
//     }

//     private CurrentAddress addCurrentAddresses(CurrentAddressDTO dto, PrimaryDetails primaryDetails) {
//         Optional<CurrentAddress> permanent = currentAddressRepository.findByPrimaryDetails(primaryDetails);
//         if (permanent.isPresent()) {
//             currentAddressRepository.delete(permanent.get());
//         }
//         CurrentAddress address = new CurrentAddress();
//         address.setSameAsPermanentAddress(dto.getSameAsPermanentAddress());
//         address.setHouseNumber(dto.getHouseNumber());
//         address.setStreetName(dto.getStreetName());
//         address.setTown(dto.getTown());
//         address.setPincode(dto.getPincode());
//         address.setState(dto.getState());
//         address.setCity(dto.getCity());
//         address.setStayFrom(dto.getStayFrom());
//         address.setStayTo(dto.getStayTo());
//         address.setEmergencyContactNumber(dto.getEmergencyContactNumber());
//         address.setEmergencyContactNameAndRelationship(dto.getEmergencyContactNameAndRelationship());
//         address.setPrimaryDetails(primaryDetails); // Associate with primary details
//         return currentAddressRepository.save(address);

//     }

//     private List<AddressDetails> addAddresses(PrimaryDetails obj, List<AddressDetailsDTO> addressDetails) {
//         List<AddressDetails> permanent = addressDetailsRepository.findByPrimaryDetails(obj);
//         if (!permanent.isEmpty()) {
//             addressDetailsRepository.deleteAll(permanent);
//         }
//         List<AddressDetails> list = new ArrayList<>();
//         for (AddressDetailsDTO lists : addressDetails) {
//             AddressDetails address = new AddressDetails();
//             address.setPincode(lists.getPincode());
//             address.setAddressLine(lists.getAddressLine());
//             address.setCountry(lists.getCountry());
//             address.setStayFrom(lists.getStayFrom());
//             address.setContactNumberWithRelationship(lists.getContactNumberWithRelationship());
//             address.setStayTo(lists.getStayTo());
//             address.setPrimaryDetails(obj);
//             list.add(address);
//         }
//         return list;
//     }

//     private List<EducationalQualification> addEducationalQualifications(List<EducationalQualificationDTO> educationalQualificationsDTO, PrimaryDetails primaryDetails) {
//         List<EducationalQualification> educationalQualifications = new ArrayList<>();
//         List<EducationalQualification> education = educationalQualificationRepository.findAllByPrimaryDetails(primaryDetails);
//         if (!education.isEmpty()) {
//             educationalQualificationRepository.deleteAll(education);
//         }
//         for (EducationalQualificationDTO eduDTO : educationalQualificationsDTO) {
//             EducationalQualification edu = new EducationalQualification();
//             edu.setDegreeName(eduDTO.getDegreeName());
//             edu.setSubject(eduDTO.getSubject());
//             edu.setPassingYear(eduDTO.getPassingYear());
//             edu.setRollNumber(eduDTO.getRollNumber());
//             edu.setGradeOrPercentage(eduDTO.getGradeOrPercentage());
//             edu.setPrimaryDetails(primaryDetails);
//             educationalQualifications.add(edu);
//         }
//         return educationalQualifications;
//     }

//     private List<DocumentCertificates> addDocumentCertificate(List<DocumentCertificateDTO> documents, PrimaryDetails obj) {
//         List<DocumentCertificates> documentCertificates = new ArrayList<>();
//         List<DocumentCertificates> doc = documentCertificatesRepository.findAllByPrimaryDetails(obj);
//         if (!doc.isEmpty()) {
//             documentCertificatesRepository.deleteAll(doc);
//         }
//         for (DocumentCertificateDTO dto : documents) {
//             DocumentCertificates document = new DocumentCertificates();
//             document.setDocumentType(dto.getDocumentType());
//             document.setDegreeCertificate(dto.getDegreeCertificate());
//             document.setDegreeCertificateUrl(dto.getDegreeCertificateUrl());
//             document.setPassingCertificate(dto.getPassingCertificate());
//             document.setPassingCertificateUrl(dto.getPassingCertificateUrl());
//             document.setPrimaryDetails(obj);
//             documentCertificates.add(document);
//         }
//         return documentCertificates;
//     }

//     private List<EmploymentHistory> addEmploymentHistory(List<EmploymentHistoryDTO> employmentHistoryDTO, PrimaryDetails primaryDetails) {
//         List<EmploymentHistory> doc = employmentHistoryRepository.findAllByPrimaryDetails(primaryDetails);
//         if (!doc.isEmpty()) {
//             employmentHistoryRepository.deleteAll(doc);
//         }
//         List<EmploymentHistory> employmentHistories = new ArrayList<>();
//         for (EmploymentHistoryDTO empDTO : employmentHistoryDTO) {
//             EmploymentHistory employmentHistory = new EmploymentHistory();
//             employmentHistory.setPreviousEmployerName(empDTO.getPreviousEmployerName());
//             employmentHistory.setEmployerAddress(empDTO.getEmployerAddress());
//             employmentHistory.setTelephoneNumber(empDTO.getTelephoneNumber());
//             employmentHistory.setEmployeeCode(empDTO.getEmployeeCode());
//             employmentHistory.setDesignation(empDTO.getDesignation());
//             employmentHistory.setDepartment(empDTO.getDepartment());
//             employmentHistory.setManagerName(empDTO.getManagerName());
//             employmentHistory.setManagerEmail(empDTO.getManagerEmail());
//             employmentHistory.setManagerContactNo(empDTO.getManagerContactNo());
//             employmentHistory.setReasonsForLeaving(empDTO.getReasonsForLeaving());
//             employmentHistory.setEmploymentStartDate(empDTO.getEmploymentStartDate());
//             employmentHistory.setEmploymentEndDate(empDTO.getEmploymentEndDate());
//             employmentHistory.setEmploymentType(empDTO.getEmploymentType());
//             employmentHistory.setExperienceCertificateUrl(empDTO.getExperienceCertificateUrl());
//             employmentHistory.setRelievingLetterUrl(empDTO.getRelievingLetterUrl());
//             employmentHistory.setLastMonthSalarySlipUrl(empDTO.getLastMonthSalarySlipUrl());
//             employmentHistory.setAppointmentLetterUrl(empDTO.getAppointmentLetterUrl());
//             employmentHistory.setPrimaryDetails(primaryDetails);
//             employmentHistories.add(employmentHistory);
//         }
//         return employmentHistories;
//     }

//     private List<ProfessionalReferences> addProfessionalReferences(List<ProfessionalReferencesDTO> referencesDTOs, PrimaryDetails primaryDetails) {
//         List<ProfessionalReferences> professionalReferences = new ArrayList<>();
//         List<ProfessionalReferences> professionalRef = professionalReferencesRepository.findAllByPrimaryDetails(primaryDetails);
//         if (!professionalRef.isEmpty()) {
//             professionalReferencesRepository.deleteAll(professionalRef);
//         }
//         for (ProfessionalReferencesDTO referenceDTO : referencesDTOs) {
//             ProfessionalReferences reference = new ProfessionalReferences();
//             reference.setProfessionalId(referenceDTO.getProfessionalId()); // Assuming you want to set this
//             reference.setName(referenceDTO.getName());
//             reference.setDesignation(referenceDTO.getDesignation());
//             reference.setEmail(referenceDTO.getEmail());
//             reference.setContactNumber(referenceDTO.getContactNumber());
//             reference.setPrimaryDetails(primaryDetails); // Associate with primary details
//             professionalReferences.add(reference);
//         }
//         return professionalReferences;
//     }

//     private List<RelativeInfo> addRelativeInfo(List<RelativeInfoDTO> relativeInfoDTOs, EmployeeRelative employeeRelative) {
//         List<RelativeInfo> relativeInfoList = new ArrayList<>();
//         List<RelativeInfo> relativeIn = relativeInfoRepository.findAllByEmployeeRelative(employeeRelative);
//         if (!relativeIn.isEmpty()) {
//             relativeInfoRepository.deleteAll(relativeIn);
//         }
//         for (RelativeInfoDTO relativeInfoDTO : relativeInfoDTOs) {
//             RelativeInfo relativeInfo = new RelativeInfo();
//             relativeInfo.setName(relativeInfoDTO.getName());
//             relativeInfo.setEmployeeId(relativeInfoDTO.getEmployeeId());
//             relativeInfo.setRelationship(relativeInfoDTO.getRelationship());
//             relativeInfo.setDepartment(relativeInfoDTO.getDepartment());
//             relativeInfo.setLocation(relativeInfoDTO.getLocation());
//             relativeInfo.setRemarks(relativeInfoDTO.getRemarks());
//             relativeInfo.setEmployeeRelative(employeeRelative);  // Set the relationship with PrimaryDetails

//             relativeInfoList.add(relativeInfo);
//         }

//         return relativeInfoRepository.saveAll(relativeInfoList);
//     }

//     private PassportDetails addPassportDetails(PassportDetailsDTO passportDetailsDTO, PrimaryDetails primaryDetails) {
//         Optional<PassportDetails> permanent = passportDetailsRepository.findByPrimaryDetails(primaryDetails);
//         permanent.ifPresent(passportDetailsRepository::delete);

//         PassportDetails passportDetails = new PassportDetails();
//         passportDetails.setPassportNumber(passportDetailsDTO.getPassportNumber());
//         passportDetails.setIssueDate(passportDetailsDTO.getIssueDate()); // Adjusted field name
//         passportDetails.setPlaceOfIssue(passportDetailsDTO.getPlaceOfIssue());
//         passportDetails.setExpiryDate(passportDetailsDTO.getExpiryDate());
//         passportDetails.setCountryOfIssue(passportDetailsDTO.getCountryOfIssue());
//         passportDetails.setNationality(passportDetailsDTO.getNationality());
//         passportDetails.setPrimaryDetails(primaryDetails); // Associate with primary details
//         return passportDetails;
//     }

//     private EmployeeRelative addEmployeeRelative(EmployeeRelativeDTO employeeRelativeDTO, PrimaryDetails primaryDetails) {
//         Optional<EmployeeRelative> permanent = employeeRelativeRepository.findByPrimaryDetails(primaryDetails);
//         if (permanent.isPresent()) {
//             employeeRelativeRepository.delete(permanent.get());
//         }
//         EmployeeRelative employeeRelative = new EmployeeRelative();
//         employeeRelative.setHasRelative(employeeRelativeDTO.getHasRelative());
//         employeeRelative.setPrimaryDetails(primaryDetails);
//         EmployeeRelative employee = employeeRelativeRepository.save(employeeRelative);
//         employeeRelative.setRelativeInfoList(addRelativeInfo(employeeRelativeDTO.getRelativeInfoDTOS(), employee));
//         return employee;
//     }

//     private VisaStatus addVisaStatus(VisaStatusDTO visaStatusDTO, PrimaryDetails primaryDetails) {
//         Optional<VisaStatus> permanent = visaStatusRepository.findByPrimaryDetails(primaryDetails);
//         if (permanent.isPresent()) {
//             visaStatusRepository.delete(permanent.get());
//         }
//         VisaStatus visaStatus = new VisaStatus();
//         visaStatus.setCitizen(visaStatusDTO.getCitizen());  // Fixed method call
//         visaStatus.setExpatOnGreenCard(visaStatusDTO.getExpatOnGreenCard());
//         visaStatus.setExpatOnWorkPermit(visaStatusDTO.getExpatOnWorkPermit());
//         visaStatus.setExpatOnPermanentResidencyPermit(visaStatusDTO.getExpatOnPermanentResidencyPermit());
//         visaStatus.setAnyOtherStatus(visaStatusDTO.getAnyOtherStatus());
//         visaStatus.setPrimaryDetails(primaryDetails);
//         return visaStatus;
//     }

//     private WorkPermit addWorkPermit(WorkPermitDTO workPermitDTO, PrimaryDetails primaryDetails) {
//         Optional<WorkPermit> permanent = workPermitRepository.findByPrimaryDetails(primaryDetails);
//         if (permanent.isPresent()) {
//             workPermitRepository.delete(permanent.get());
//         }
//         WorkPermit workPermit = new WorkPermit();
//         workPermit.setLegalRightToWork(workPermitDTO.getLegalRightToWork());
//         workPermit.setWorkPermitDetails(workPermitDTO.getWorkPermitDetails());
//         workPermit.setWorkPermitValidTill(workPermitDTO.getWorkPermitValidTill());
//         workPermit.setPassportCopy(workPermitDTO.getPassportCopy());
//         workPermit.setPassportCopyPath(workPermitDTO.getPassportCopyPath());
//         workPermit.setPrimaryDetails(primaryDetails);  // Associate with primary details

//         return workPermit;
//     }


//     private OtherDetails addOtherDetails(OtherDetailsDTO otherDetailsDTO, PrimaryDetails primaryDetails) {
//         Optional<OtherDetails> permanent = otherDetailsRepository.findByPrimaryDetails(primaryDetails);
//         if (permanent.isPresent()) {
//             otherDetailsRepository.delete(permanent.get());
//         }
//         OtherDetails otherDetails = new OtherDetails();
//         otherDetails.setOtherId(otherDetailsDTO.getOtherId());
//         otherDetails.setIllness(otherDetailsDTO.getIllness());
//         otherDetails.setSelfIntroduction(otherDetailsDTO.getSelfIntroduction());
//         otherDetails.setDeclarationAccepted(otherDetailsDTO.getDeclarationAccepted());
//         otherDetails.setPrimaryDetails(primaryDetails);

//         return otherDetails;
//     }




//     private Preview addPreview(PreviewDTO previewDTO, PrimaryDetails primaryDetails) {
//         Optional<Preview> permanent = previewRepository.findByPrimaryDetails(primaryDetails);
//         if (permanent.isPresent()) {
//             previewRepository.delete(permanent.get());
//         }

//         Preview preview = new Preview();
//         preview.setEmployeeSignatureUrl(previewDTO.getEmployeeSignatureUrl());
//         preview.setSignatureDate(previewDTO.getSignatureDate());
//         preview.setPlace(previewDTO.getPlace());
//         preview.setPrimaryDetails(primaryDetails); // Associate with primary details
//         return preview;
//     }


//     @Override
//     public List<PrimaryDetails> getAllPrimaryDetails() {
//         List<PrimaryDetails> primaryDetails = primaryDetailsRepo.findAll();
//         return primaryDetails;

//     }

//     @Override
//     public PrimaryDetails getPrimaryDetailsById(Long primaryId) {
//         PrimaryDetails primaryDetail = primaryDetailsRepo.findById(primaryId).orElse(null);
//         return primaryDetail;
//     }

//     @Override
//     public PrimaryDetails updatePrimaryDetailsById(Long primaryId, PrimaryDetails primaryDetails) {
//         Optional<PrimaryDetails> existingPrimaryDetails = primaryDetailsRepo.findById(primaryId);
//         if (existingPrimaryDetails.isPresent()) {
//             existingPrimaryDetails.get().setFullName(primaryDetails.getFullName());
//             existingPrimaryDetails.get().setMobileNumber(primaryDetails.getMobileNumber());
//             existingPrimaryDetails.get().setDateOfBirth(primaryDetails.getDateOfBirth());
//             existingPrimaryDetails.get().setEmail(primaryDetails.getEmail());
//             existingPrimaryDetails.get().setPassword(primaryDetails.getPassword());
//             existingPrimaryDetails.get().setCreatedDate(LocalDateTime.now());
//             primaryDetailsRepo.save(existingPrimaryDetails.get());
//             return existingPrimaryDetails.get();
//         } else {
//             throw new NotFoundException("Record not available");
//         }
//     }

//     @Override
//     public PrimaryDetails deletePrimaryDetailsById(Long primaryId) {
//         PrimaryDetails primaryDetailsDelete = primaryDetailsRepo.findById(primaryId).orElse(null);
//         if (primaryDetailsDelete != null) {
//             primaryDetailsRepo.delete(primaryDetailsDelete);
//             return primaryDetailsDelete;
//         } else {
//             throw new RuntimeException("PrimaryDetails not found with id: " + primaryId);
//         }

//     }

//     @Override
//     public String sendEmailOtp(SendOtpDto sendotp) {
//         Random random = new Random();
//         // Generate a random number between 100000 (inclusive) and 999999 (inclusive)
//         String randomSixDigitNumber = String.valueOf(100000 + random.nextInt(900000));
//         emailService.sendEmail(sendotp.getEmail(), "", randomSixDigitNumber, EmailConstant.SIGN_UP_OTP_SUBJECT, EmailConstant.SIGN_UP_OTP_TEMPLATE_NAME);

//         // Store OTP in Redis with 2-minute expiration
//         redisTemplate.opsForValue().set(sendotp.getEmail(), randomSixDigitNumber, OTP_EXPIRATION_TIME, TimeUnit.MINUTES);

//         return randomSixDigitNumber; // Return the OTP
//     }


//     //	public boolean validateOtp(String email, String otp) {
// //		// Retrieve OTP from Redis
// //		String cachedOtp = (String) redisTemplate.opsForValue().get(email);
// //
// //		// Check if the OTP is correct
// //		if (cachedOtp != null && cachedOtp.equals(otp)) {
// //			// OTP is valid, remove it from Redis after use
// //			redisTemplate.delete(email);
// //			return true;
// //		}
// //
// //		return false;
// //	}
//     public boolean validateOtp(String email, String otp) {
//         String cachedOtp = (String) redisTemplate.opsForValue().get(email);
//         System.out.println("Retrieved OTP from Redis for " + email + ": " + cachedOtp);

//         if (cachedOtp != null && cachedOtp.equals(otp)) {
//             redisTemplate.delete(email);
//             return true;
//         }
//         return false;
//     }


//     @Override
//     public String resendOtp(String email) {
//         // Check if an OTP exists in Redis
//         String cachedOtp = (String) redisTemplate.opsForValue().get(email);

//         if (cachedOtp != null) {
//             // If OTP exists, send the same OTP again
//             try {
//                 // emailService.sendEmail(email, "", cachedOtp, EmailConstant.SIGN_UP_OTP_SUBJECT, EmailConstant.SIGN_UP_OTP_TEMPLATE_NAME);
//                 return cachedOtp; // Return the existing OTP
//             } catch (Exception e) {
//                 // Handle email sending error
//                 throw new RuntimeException("Failed to send OTP email. Please try again later.");
//             }
//         } else {
//             // Generate a new OTP if it does not exist or has expired
//             return sendEmailOtp(new SendOtpDto(email));
//         }
//     }

//     @Override
//     public PrimaryDetails findByEmail(String email) {

//         return primaryDetailsRepo.findByEmail(email);
//     }

//     @Override
//     public void createUser(PrimaryDetails user) {
//         // Log the user details before saving
//         System.out.println("Saving User: " + user);

//         // Encode the password
//         user.setPassword(passwordEncoder.encode(user.getPassword()));
//         primaryDetailsRepo.save(user); // Save the user to the database
//     }

//     @Override
//     public void sendPasswordResetEmail(String email) throws Exception {
//         PrimaryDetails user = primaryDetailsRepo.findByEmail(email);
//         if (user == null) {
//             throw new Exception("User not found");
//         }

//         String createdDate = user.getCreatedDate().toString(); // Use appropriate format
//         String resetToken = generateResetToken(email);
//         String resetLink = "http://localhost:3000/resetPassword?token=" + resetToken;

//         String subject = "Password Reset Request";
//         String templateName = "passwordResetTemplate";  // Thymeleaf template for the email
//         // emailService.sendEmail(email, createdDate, resetToken, subject, templateName);
//     }


//     @Override
//     public String generateResetToken(String email) {
//         String token = UUID.randomUUID().toString();
//         // Store the token with the user reference
//         PrimaryDetails user = primaryDetailsRepo.findByEmail(email);
//         JwtEntity jwtEntity = new JwtEntity();
//         jwtEntity.setJti(token);
//         jwtEntity.setValidSession(true);
//         jwtEntity.setPrimaryId(user.getPrimaryId()); // Assuming you have the user ID
//         jwtRepository.save(jwtEntity);
//         return token;

//     }


//     @Override
//     public void resetPassword(String token, String newPassword) throws Exception {

//         // Optional<JwtEntity> optionalJwtEntity = jwtRepository.findByJtiAndValidSession(token, true);
// //optional is empty
//         if (!optionalJwtEntity.isPresent()) {
//             throw new Exception("Invalid token or user not found");
//         }

//         JwtEntity jwtEntity = optionalJwtEntity.get();

//         PrimaryDetails user = primaryDetailsRepo.findById((long)jwtEntity.getPrimaryId())
//                 .orElseThrow(() -> new Exception("User not found with primaryId: " + jwtEntity.getPrimaryId()));

//         // Encode the new password
//         String encodedPassword = passwordEncoder.encode(newPassword);
//         user.setPassword(encodedPassword);
//         primaryDetailsRepo.save(user);
//         System.out.println("Password updated successfully for user: " + user.getEmail());
//     }


//     @Override
//     public String resendActivationLink(String email) {
//         PrimaryDetails user = findByEmail(email);
//         if (user == null) {
//             throw new NotFoundException("User not found with this email.");
//         }

//         // Generate a new activation link with a unique identifier or timestamp
//         String activationLink = EmailConstant.ACTIVE_SIGNUP_LINK + "?email=" + email + "&timestamp=" + System.currentTimeMillis();

//         // Sending the email with the activation link
//         // emailService.sendEmail(email, user.getCreatedDate().toString(), "", EmailConstant.RESEND_LINK_SUBJECT, EmailConstant.SIGN_UP_LINK_TEMPLATE_NAME);

//         return activationLink; // Return the new activation link or a success message
//     }

//     public void tokenLogout(String jwtToken) {

//         // Claims claims = jwtUtil.getClaims(jwtToken);
//         // Optional<JwtEntity> jwtEntity = jwtRepository.findByJtiAndValidSession(claims.getId(), true);
//         // if (jwtEntity.isPresent()) {
//         //     jwtEntity.get().setValidSession(false);
//         //     jwtRepository.saveAndFlush(jwtEntity.get());
//         // }
//     }

//     @Override
//     public PrimaryPreviewResponse getPrimaryDetails(Long primaryId) {
//         PrimaryDetails primaryDetail = primaryDetailsRepo.findById(primaryId).orElse(null);
//         PrimaryPreviewResponse primaryPreviewResponse = new PrimaryPreviewResponse();
//         primaryPreviewResponse.setPrimaryId((long)primaryDetail.getPrimaryId());
//         primaryPreviewResponse.setEmail(primaryDetail.getEmail());
//         primaryPreviewResponse.setFullName(primaryDetail.getFullName());
//         primaryPreviewResponse.setDateOfBirth(primaryDetail.getDateOfBirth());
//         primaryPreviewResponse.setMobileNumber(primaryDetail.getMobileNumber());
//         primaryPreviewResponse.setCreatedDate(primaryDetail.getCreatedDate());
//         primaryPreviewResponse.setAddressDetails(primaryDetail.getAddressDetails());
//         primaryPreviewResponse.setPermanentAddress(primaryDetail.getPermanentAddress());
//         primaryPreviewResponse.setCurrentAddresses(primaryDetail.getCurrentAddresses());
//         primaryPreviewResponse.setDocuments(primaryDetail.getDocumentCertificates());
//         primaryPreviewResponse.setEducationalQualifications(primaryDetail.getEducationalQualifications());
//         primaryPreviewResponse.setEmploymentHistories(primaryDetail.getEmploymentHistories());
//         primaryPreviewResponse.setProfessionalReferences(primaryDetail.getProfessionalReferences());
//         primaryPreviewResponse.setPassportDetails(primaryDetail.getPassportDetails());
//         primaryPreviewResponse.setVisaStatus(primaryDetail.getVisaStatus());
//         primaryPreviewResponse.setWorkPermit(primaryDetail.getWorkPermit());
//         primaryPreviewResponse.setEmployeeRelatives(primaryDetail.getEmployeeRelative());
//         primaryPreviewResponse.setOtherDetails(primaryDetail.getOtherDetails());
//         primaryPreviewResponse.setPersonalDetails(primaryDetail.getPersonalDetails());
//         primaryPreviewResponse.setRoleName(primaryDetail.getRoleName());
//         return primaryPreviewResponse;
//     }

//     @Override
//     public String sendPreviewDetailsToHR(PreviewDetailsDTO previewDetailsDTO) {

//         List<PrimaryDetails> primaryDetails = primaryDetailsRepo.findByRoleName("HR");
//         if (!primaryDetails.isEmpty()) {
//             for (PrimaryDetails hrEmail : primaryDetails) {
//                 emailService.sendPreviewEmailToHr(
//                         hrEmail.getEmail(),
//                         previewDetailsDTO.getEmployeeSignatureUrl(),
//                         previewDetailsDTO.getSignatureDate(),
//                         previewDetailsDTO.getPlace(),
//                         EmailConstant.PREVIEW_DETAILS_SUBJECT,
//                         EmailConstant.PREVIEW_DETAILS_TEMPLATE_NAME,
//                         EmailConstant.PREVIEW_DETAILS_LINK + previewDetailsDTO.getPrimaryId()
//                 );
//             }
//         }
//         return "Send Email Successfully ";

//     }
// }











