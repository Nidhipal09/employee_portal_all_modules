package com.employeeportal.filter;

import com.employeeportal.config.ApplicationConstant;
import com.employeeportal.exception.EmployeeNotFoundException;
import com.employeeportal.exception.ExceptionResponse;
import com.employeeportal.exception.InvalidTokenException;
import com.employeeportal.exception.TokenExpireException;
import com.employeeportal.exception.UnauthorizedException;
import com.employeeportal.model.JwtEntity;
import com.employeeportal.model.onboarding.EmployeeOrganizationDetails;
import com.employeeportal.model.onboarding.Role;
import com.employeeportal.model.registration.Employee;
import com.employeeportal.model.registration.EmployeeReg;
import com.employeeportal.repository.onboarding.EmployeeOrganizationDetailsRepository;
import com.employeeportal.repository.onboarding.RoleRepository;
import com.employeeportal.repository.registration.EmployeeRegRepository;
import com.employeeportal.repository.registration.EmployeeRepository;
import com.employeeportal.serviceImpl.logout.TokenBlacklistService;
import com.employeeportal.repository.JwtRepository;
import com.employeeportal.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.Authentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final UserDetailsService service;
    private final JwtRepository jwtRepository;
    private final EmployeeRegRepository employeeRegRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeOrganizationDetailsRepository employeeOrganizationDetailsRepository;
    private final RoleRepository roleRepository;
    private TokenBlacklistService tokenBlacklistService;

    @Autowired
    public JwtFilter(JwtUtil jwtUtil, UserDetailsService service, JwtRepository jwtRepository,
            EmployeeRegRepository employeeRegRepository,
            EmployeeOrganizationDetailsRepository employeeOrganizationDetailsRepository,
            RoleRepository roleRepository, EmployeeRepository employeeRepository, 
            TokenBlacklistService tokenBlacklistService) {
        this.jwtUtil = jwtUtil;
        this.service = service;
        this.jwtRepository = jwtRepository;
        this.employeeRegRepository = employeeRegRepository;
        this.employeeRepository = employeeRepository;
        this.employeeOrganizationDetailsRepository = employeeOrganizationDetailsRepository;
        this.roleRepository = roleRepository;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        System.out.println("11111111111111111111111111111111111  inside Jwt filter");        
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);

            if(tokenBlacklistService.isTokenBlacklisted(jwt)) {
                throw new UnauthorizedException("User is logged out. Please login again.");
            }

            final String userEmail = jwtUtil.extractUsername(jwt);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa"+authentication);
            System.out.println("bbbbbbbbbbbbbbbbbbbbbb"+userEmail);

            if (userEmail != null && authentication == null) {
                UserDetails userDetails = this.service.loadUserByUsername(userEmail);

                System.out.println("cccccccccccccccccccccccccccccc"+userDetails);
                if (jwtUtil.validateToken(jwt, userDetails)) {
                    System.out.println("2222222222222222222222222222222222222222  inside Jwt filter");
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());
                    System.out.println("33333333333333333333333333333333333  inside Jwt filter"+authToken);        

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }else{
                    throw new InvalidTokenException("Invalid token: Please enter the valid JWT token.");
                }
            }

            filterChain.doFilter(request, response);
        } catch (UnauthorizedException ex) {
            handleGenericException(response, ex, HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        } catch (TokenExpireException | ExpiredJwtException ex) {
            handleGenericException(response, ex, HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        } catch (EmployeeNotFoundException | NoSuchElementException ex) {
            handleGenericException(response, ex, HttpStatus.NOT_FOUND.value(),
                    ApplicationConstant.AUTHORIZATION_ERROR);
        } catch (Exception ex) {
            handleGenericException(response, ex, HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

    private void handleGenericException(HttpServletResponse response, final Exception ex, Integer code, String message)
            throws IOException {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setCode(code);
        exceptionResponse.setMessage(message);
        response.setStatus(code);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(new ObjectMapper().writeValueAsString(exceptionResponse));
        response.getWriter().flush();
    }
}
