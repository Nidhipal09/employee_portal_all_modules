package com.employeeportal.config;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.repository.PrimaryDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class AdminConfig {

    private final PrimaryDetailsRepository primaryDetailsRepository;

    private final AdminProperties adminProperties;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminConfig(PrimaryDetailsRepository userRepository, AdminProperties adminProperties, PasswordEncoder passwordEncoder) {
        this.primaryDetailsRepository = userRepository;
        this.adminProperties = adminProperties;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (!primaryDetailsRepository.existsByEmail(adminProperties.getEmailAddress())) {
            saveAdminDetails();
        }
    }

    private void saveAdminDetails() {
        PrimaryDetails user = new PrimaryDetails();
            user.setFullName(adminProperties.getUserName());
            user.setEmail(adminProperties.getEmailAddress());
            user.setPassword(passwordEncoder.encode(adminProperties.getPassword()));
            user.setRoleName("SUPER_ADMIN");
        primaryDetailsRepository.save(user);
    }
}
