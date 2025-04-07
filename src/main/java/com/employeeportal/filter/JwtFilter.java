package com.employeeportal.filter;
import com.employeeportal.config.ApplicationConstant;
import com.employeeportal.exception.EmployeeNotFoundException;
import com.employeeportal.exception.ExceptionResponse;
import com.employeeportal.exception.TokenExpireException;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.JwtEntity;
import com.employeeportal.model.Users;
import com.employeeportal.repository.PrimaryDetailsRepository;
import com.employeeportal.repository.JwtRepository;
import com.employeeportal.repository.UsersRepository;
import com.employeeportal.serviceImpl.EmployeeDetailServiceImpl;
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
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

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

    private final EmployeeDetailServiceImpl service;
    private final JwtRepository jwtRepository;
    private final PrimaryDetailsRepository primaryDetailsRepository;
    private final UsersRepository usersRepository;


    @Autowired
    public JwtFilter(JwtUtil jwtUtil, EmployeeDetailServiceImpl service, JwtRepository jwtRepository, PrimaryDetailsRepository primaryDetailsRepository, UsersRepository usersRepository) {
        this.jwtUtil = jwtUtil;
        this.service = service;
        this.jwtRepository = jwtRepository;
        this.primaryDetailsRepository = primaryDetailsRepository;

        this.usersRepository = usersRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authorizationHeader = httpServletRequest.getHeader(ApplicationConstant.HEADERS);

            String token = null;
            String userName = null;

            if (authorizationHeader != null && authorizationHeader.startsWith(ApplicationConstant.AUTH_TYPE)) {
                token = authorizationHeader.substring(7);
                userName = jwtUtil.extractUsername(token);
            }

            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = service.loadUserByUsername(userName);
                if (jwtUtil.validateToken(token, userDetails)) {
                    Claims claims = jwtUtil.getClaims(token);
                    Optional<JwtEntity> jwtEntity = jwtRepository.findByJtiAndValidSession(claims.getId(), false);
                    if (jwtEntity.isPresent()) {
                        throw new TokenExpireException();
                    } else {
                        String email = (String) claims.get("emailId");
                        PrimaryDetails employee = primaryDetailsRepository.findByEmail(email);
                        Users users = usersRepository.findByEmail(email);
                        String roleName = "";
                        if(employee == null && users == null) {
                           throw new EmployeeNotFoundException();
                        }
                        if(employee!= null){
                            roleName=employee.getRoleName();
                        } else if (users!=null) {
                            roleName=users.getRoleName();

                        }
                        Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
                            authorities =
                                    Arrays.stream(("ROLE_"+roleName).split(","))
                                            .map(SimpleGrantedAuthority::new)
                                            .collect(Collectors.toList());
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                        usernamePasswordAuthenticationToken
                                .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                } else {
                    throw new TokenExpireException();
                }
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (TokenExpireException | ExpiredJwtException ex) {
            handleGenericException(httpServletResponse, ex, HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        } catch (EmployeeNotFoundException | NoSuchElementException ex) {
            handleGenericException(httpServletResponse, ex, HttpStatus.NOT_FOUND.value(), ApplicationConstant.AUTHORIZATION_ERROR);
        } catch (Exception ex) {
            handleGenericException(httpServletResponse, ex, HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

    private void handleGenericException(HttpServletResponse response, final Exception ex, Integer code, String message) throws IOException {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setCode(code);
        exceptionResponse.setMessage(message);
        response.setStatus(code);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(new ObjectMapper().writeValueAsString(exceptionResponse));
        response.getWriter().flush();
    }
}
