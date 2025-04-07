package com.employeeportal.controller;

import com.employeeportal.config.ApplicationConstant;
import com.employeeportal.model.*;
import com.employeeportal.repository.JwtRepository;
import com.employeeportal.repository.PrimaryDetailsRepository;
import com.employeeportal.repository.UsersRepository;
import com.employeeportal.service.PrimaryDetailsService;
import com.employeeportal.serviceImpl.EmployeeDetailServiceImpl;
import com.employeeportal.util.JwtUtil;
import com.employeeportal.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/public")
@CrossOrigin(origins = "*")
public class AuthController {
    private final ResponseUtil responseUtil;
    private final JwtUtil jwtUtil;
    // Inject the JwtUtil instance
    private final PrimaryDetailsRepository primaryDetailsRepository;

    private final AuthenticationManager authenticationManager;

    private final EmployeeDetailServiceImpl employeeService;
    @Autowired
    private JwtRepository jwtRepository;
    @Autowired
    private PrimaryDetailsService primaryDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsersRepository usersRepo;


    @Autowired
    public AuthController(ResponseUtil responseUtil, JwtUtil jwtUtil, PrimaryDetailsRepository primaryDetailsRepository, AuthenticationManager authenticationManager, EmployeeDetailServiceImpl employeeService) {
        this.responseUtil = responseUtil;
        this.jwtUtil = jwtUtil;
        this.primaryDetailsRepository = primaryDetailsRepository;
        this.authenticationManager = authenticationManager;
        this.employeeService = employeeService;
    }

    //    @PostMapping("/login")
//    public ResponseEntity<ResponseDTO> generateToken(@RequestBody LoginRequest authRequest) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
//            );
//            LoginResponse loginResponse = employeeService.findEmployeeDetails(authRequest.getEmail());
//            loginResponse.setToken(jwtUtil.generateToken(authRequest.getEmail()));
//            ResponseDTO response = responseUtil.prepareResponseDto(loginResponse,
//                    loginResponse.getRoleName()+ ApplicationConstant.LOGIN_RESPONSE,
//                    HttpStatus.OK.value(), true);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception ex) {
//            ResponseDTO response = responseUtil.prepareResponseDto(null,
//                    ApplicationConstant.AUTHORIZATION_ERROR,
//                    HttpStatus.UNAUTHORIZED.value(), false);
//            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//        }
//    }
//    @PostMapping("/login")
//    public ResponseEntity<ResponseDTO> generateToken(@RequestBody LoginRequest authRequest) {
//        try {
//            String storedEncodedPassword = primaryDetailsRepository.findPasswordByEmail(authRequest.getEmail());
//
//            if (!primaryDetailsRepository.existsByEmail(authRequest.getEmail())) {
//                throw new BadCredentialsException(ApplicationConstant.AUTHORIZATION_EMAIL_ERROR);
//
//            }
//            if (!passwordEncoder.matches(authRequest.getPassword(), storedEncodedPassword)) {
//                throw new BadCredentialsException(ApplicationConstant.AUTHORIZATION_PASSWORD_ERROR);
//
//            }
//
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
//            );
//
//            LoginResponse loginResponse = employeeService.findEmployeeDetails(authRequest.getEmail());
//            loginResponse.setToken(jwtUtil.generateToken(authRequest.getEmail()));
//
//
//            ResponseDTO response = responseUtil.prepareResponseDto(loginResponse,
//                    loginResponse.getRoleName() + ApplicationConstant.LOGIN_RESPONSE,
//                    HttpStatus.OK.value(), true);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//
//        } catch (BadCredentialsException ex) {
//
//            ResponseDTO response = responseUtil.prepareResponseDto(null,
//                    ex.getMessage(),
//                    HttpStatus.UNAUTHORIZED.value(), false);
//            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//
//        } catch (UsernameNotFoundException ex) {
//
//            ResponseDTO response = responseUtil.prepareResponseDto(null,
//                    ex.getMessage(),
//                    HttpStatus.NOT_FOUND.value(), false);
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//
//        } catch (Exception ex) {
//
//            ResponseDTO response = responseUtil.prepareResponseDto(null,
//                    ApplicationConstant.AUTHORIZATION_ERROR,
//                    HttpStatus.UNAUTHORIZED.value(), false);
//            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> generateToken(@RequestBody LoginRequest authRequest) {
        try {
            // Try to find the email in PrimaryDetails first
            PrimaryDetails primaryDetails = primaryDetailsRepository.findByEmail(authRequest.getEmail());
            Users user = usersRepo.findByEmail(authRequest.getEmail());

            if (primaryDetails == null && user == null) {
                throw new BadCredentialsException(ApplicationConstant.AUTHORIZATION_EMAIL_ERROR);
            }

            String storedEncodedPassword = null;

            // If email is found in PrimaryDetails, get the password from PrimaryDetails
            if (primaryDetails != null) {
                storedEncodedPassword = primaryDetails.getPassword();
            }

            // If email is found in Users, get the password from Users
            if (user != null) {
                storedEncodedPassword = user.getPassword();
            }

            // Check if the password matches the stored one
            if (storedEncodedPassword == null || !passwordEncoder.matches(authRequest.getPassword(), storedEncodedPassword)) {
                throw new BadCredentialsException(ApplicationConstant.AUTHORIZATION_PASSWORD_ERROR);
            }

            // Generate token for the found user
            String email = authRequest.getEmail();
            LoginResponse loginResponse;

            // If email exists in PrimaryDetails, we create a login response from PrimaryDetails
            if (primaryDetails != null) {
                loginResponse = new LoginResponse();
                loginResponse.setEmail(primaryDetails.getEmail());
                loginResponse.setId(primaryDetails.getPrimaryId());
                loginResponse.setFullName(primaryDetails.getFullName());
                loginResponse.setRoleName(primaryDetails.getRoleName());
            }
            // If email exists in Users, we create a login response from Users
            else if (user != null) {
                loginResponse = new LoginResponse();
                loginResponse.setEmail(user.getEmail());
                loginResponse.setFullName(user.getName());
                loginResponse.setId(user.getUsersId());
                loginResponse.setRoleName(user.getRoleName());
            } else {
                throw new UsernameNotFoundException("User not found in any table.");
            }

            // Generate JWT token
            loginResponse.setToken(jwtUtil.generateToken(email));

            // Prepare and send the response
            ResponseDTO response = responseUtil.prepareResponseDto(
                    loginResponse,
                    loginResponse.getRoleName() + ApplicationConstant.LOGIN_RESPONSE,
                    HttpStatus.OK.value(),
                    true
            );

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (BadCredentialsException ex) {
            ResponseDTO response = responseUtil.prepareResponseDto(
                    null, ex.getMessage(),
                    HttpStatus.UNAUTHORIZED.value(), false
            );
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);

        } catch (UsernameNotFoundException ex) {
            ResponseDTO response = responseUtil.prepareResponseDto(
                    null, ex.getMessage(),
                    HttpStatus.NOT_FOUND.value(), false
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (Exception ex) {
            ResponseDTO response = responseUtil.prepareResponseDto(
                    null, ApplicationConstant.AUTHORIZATION_ERROR,
                    HttpStatus.UNAUTHORIZED.value(), false
            );
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }



    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        try {
            primaryDetailsService.sendPasswordResetEmail(email);
            return ResponseEntity.ok("Password reset email sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        try {
            primaryDetailsService.resetPassword(token, newPassword);
            return ResponseEntity.ok("Password reset successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {

        try {
            String jwtToken = token.substring(7);
            primaryDetailsService.tokenLogout(jwtToken);
            ResponseDTO response = responseUtil.prepareResponseDto(null,
                    ApplicationConstant.LOGOUT_RESPONSE,
                    HttpStatus.OK.value(), true);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ResponseDTO response = responseUtil.prepareResponseDto(null,
                    ApplicationConstant.AUTHORIZATION_ERROR,
                    HttpStatus.UNAUTHORIZED.value(), false);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}




