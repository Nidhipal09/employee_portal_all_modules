package com.employeeportal.util;

import com.employeeportal.model.GeneralResponses;
import com.employeeportal.model.JwtEntity;
import com.employeeportal.model.onboarding.EmployeeOrganizationDetails;
import com.employeeportal.model.onboarding.Role;
import com.employeeportal.model.registration.Employee;
import com.employeeportal.model.registration.EmployeeReg;
import com.employeeportal.repository.onboarding.EmployeeOrganizationDetailsRepository;
import com.employeeportal.repository.onboarding.RoleRepository;
import com.employeeportal.repository.registration.EmployeeRegRepository;
import com.employeeportal.repository.registration.EmployeeRepository;
import com.employeeportal.repository.JwtRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.netty.util.internal.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Value("${jwt.secret.key}")
    private String secret;
    @Value("${jwt.token.client.expiration}")
    private Long expiration;

    private final JwtRepository jwtRepository;

    private final EmployeeOrganizationDetailsRepository employeeOrganizationDetailsRepository;
    private final RoleRepository roleRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeRegRepository employeeRegRepository;

    @Autowired
    public JwtUtil(JwtRepository jwtRepository,
            EmployeeOrganizationDetailsRepository employeeOrganizationDetailsRepository,
            RoleRepository roleRepository, EmployeeRepository employeeRepository,
            EmployeeRegRepository employeeRegRepository) {
        this.employeeRegRepository = employeeRegRepository;
        this.jwtRepository = jwtRepository;
        this.employeeOrganizationDetailsRepository = employeeOrganizationDetailsRepository;
        this.roleRepository = roleRepository;
        this.employeeRepository = employeeRepository;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String email) {
        EmployeeReg employeeReg = employeeRegRepository.findByEmail(email);
        return generateToken(employeeReg);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Claims getClaims(String jwtToken) {
        return extractAllClaims(jwtToken);

    }

    public String extractJti(String token) {
        return extractClaim(token, Claims::getId); // Extract JTI from the token using Claims::getId
    }
}
