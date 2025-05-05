package com.employeeportal.serviceImpl.login;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.employeeportal.exception.NotFoundException;
import com.employeeportal.model.*;
import com.employeeportal.model.dto.*;
import com.employeeportal.model.registration.Employee;
import com.employeeportal.repository.*;
import com.employeeportal.repository.registration.EmployeeRepository;
import com.employeeportal.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.employeeportal.config.EmailConstant;
import com.employeeportal.service.EmailService;
import com.employeeportal.service.PrimaryDetailsService;
import com.employeeportal.service.login.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    private final EmployeeRepository employeeRepository;

    private final JwtRepository jwtRepository;
    private final JwtUtil jwtUtil;

    private final EmailService emailService;
    private final EmployeeRelativeRepository employeeRelativeRepository;
    private final PersonalDetailsRepository personalDetailsRepository;
    private final PermanentAddressRepository permanentAddressRepository;
    private final CurrentAddressRepository currentAddressRepository;
    private final AddressDetailsRepository addressDetailsRepository;
    private final PasswordEncoder passwordEncoder;
    // private final BCryptPasswordEncoder passwordEncoder = new
    // BCryptPasswordEncoder();
    private final RedisTemplate<String, Object> redisTemplate;
    private final DocumentCertificatesRepository documentCertificatesRepository;
    private final EducationalQualificationRepository educationalQualificationRepository;
    private final ProfessionalReferencesRepository professionalReferencesRepository;
    private final EmploymentHistoryRepository employmentHistoryRepository;
    private final PassportDetailsRepository passportDetailsRepository;
    private final VisaStatusRepository visaStatusRepository;
    private final WorkPermitRepository workPermitRepository;
    private final OtherDetailsRepository otherDetailsRepository;
    private final RelativeInfoRepository relativeInfoRepository;
    private final PreviewRepository previewRepository;

    private static final long OTP_EXPIRATION_TIME = 2; // 2 minutes

    @Autowired
    public LoginServiceImpl(EmployeeRepository employeeRepository, JwtRepository jwtRepository,
            JwtUtil jwtUtil, EmailService emailService, EmployeeRelativeRepository employeeRelativeRepository,
            PersonalDetailsRepository personalDetailsRepository, PermanentAddressRepository permanentAddressRepository,
            CurrentAddressRepository currentAddressRepository, AddressDetailsRepository addressDetailsRepository,
            PasswordEncoder passwordEncoder, RedisTemplate<String, Object> redisTemplate,
            DocumentCertificatesRepository documentCertificatesRepository,
            EducationalQualificationRepository educationalQualificationRepository,
            ProfessionalReferencesRepository professionalReferencesRepository,
            EmploymentHistoryRepository employmentHistoryRepository,
            PassportDetailsRepository passportDetailsRepository, VisaStatusRepository visaStatusRepository,
            WorkPermitRepository workPermitRepository, OtherDetailsRepository otherDetailsRepository,
            RelativeInfoRepository relativeInfoRepository, PreviewRepository previewRepository) {
        this.employeeRepository = employeeRepository;
        this.jwtRepository = jwtRepository;
        this.jwtUtil = jwtUtil;
        this.emailService = emailService;
        this.employeeRelativeRepository = employeeRelativeRepository;
        this.personalDetailsRepository = personalDetailsRepository;
        this.permanentAddressRepository = permanentAddressRepository;
        this.currentAddressRepository = currentAddressRepository;
        this.addressDetailsRepository = addressDetailsRepository;
        this.passwordEncoder = passwordEncoder;
        this.redisTemplate = redisTemplate;
        this.documentCertificatesRepository = documentCertificatesRepository;
        this.educationalQualificationRepository = educationalQualificationRepository;
        this.professionalReferencesRepository = professionalReferencesRepository;
        this.employmentHistoryRepository = employmentHistoryRepository;
        this.passportDetailsRepository = passportDetailsRepository;
        this.visaStatusRepository = visaStatusRepository;
        this.workPermitRepository = workPermitRepository;
        this.otherDetailsRepository = otherDetailsRepository;
        this.relativeInfoRepository = relativeInfoRepository;
        this.previewRepository = previewRepository;
    }

    @Override
    public void sendPasswordResetEmail(String email) throws Exception {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null) {
            throw new Exception("employee not found");
        }

        String createdDate = employee.getCreatedDate().toString(); // Use appropriate format
        String resetToken = generateResetToken(email);
        String resetLink = "http://localhost:3000/resetPassword?token=" + resetToken;

        String subject = "Password Reset Request";
        String templateName = "passwordResetTemplate"; // Thymeleaf template for the email
        emailService.sendEmail(email, createdDate, resetToken, subject, templateName);
    }

    @Override
    public String generateResetToken(String email) {
        String token = UUID.randomUUID().toString();
        // Store the token with the employee reference
        Employee employee = employeeRepository.findByEmail(email);
        JwtEntity jwtEntity = new JwtEntity();
        jwtEntity.setJti(token);
        jwtEntity.setValidSession(true);
        jwtEntity.setPrimaryId(employee.getEmployeeId()); // Assuming you have the employee ID
        jwtRepository.save(jwtEntity);
        return token;

    }

    @Override
    public void resetPassword(String token, String newPassword) throws Exception {

        Optional<JwtEntity> optionalJwtEntity = jwtRepository.findByJtiAndValidSession(token, true);
        // optional is empty
        if (!optionalJwtEntity.isPresent()) {
            throw new Exception("Invalid token or employee not found");
        }

        JwtEntity jwtEntity = optionalJwtEntity.get();

        Employee employee = employeeRepository.findById(jwtEntity.getPrimaryId())
                .orElseThrow(() -> new Exception("employee not found with primaryId: " + jwtEntity.getPrimaryId()));

        // Encode the new password
        String encodedPassword = passwordEncoder.encode(newPassword);
        employee.setPassword(encodedPassword);
        employeeRepository.save(employee);
        System.out.println("Password updated successfully for employee: " + employee.getEmail());
    }

    public void tokenLogout(String jwtToken) {

        Claims claims = jwtUtil.getClaims(jwtToken);
        Optional<JwtEntity> jwtEntity = jwtRepository.findByJtiAndValidSession(claims.getId(), true);
        if (jwtEntity.isPresent()) {
            jwtEntity.get().setValidSession(false);
            jwtRepository.saveAndFlush(jwtEntity.get());
        }
    }

}
