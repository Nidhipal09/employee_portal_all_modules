package com.employeeportal.util;


import com.employeeportal.model.GeneralResponses;
import com.employeeportal.model.JwtEntity;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.Users;
import com.employeeportal.repository.PrimaryDetailsRepository;

import com.employeeportal.repository.JwtRepository;
import com.employeeportal.repository.UsersRepository;
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

    private final PrimaryDetailsRepository employeeRepository;
    private final UsersRepository usersRepository;
    private final JwtRepository jwtRepository;

    @Autowired
    public JwtUtil(PrimaryDetailsRepository userRepository, PrimaryDetailsRepository employeeRepository, UsersRepository usersRepository, JwtRepository jwtRepository) {
        this.employeeRepository = employeeRepository;
        this.usersRepository = usersRepository;
        this.jwtRepository = jwtRepository;
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

    public String generateToken(String employeename) {
        Map<String, Object> claims = new HashMap<>();
        PrimaryDetails primaryDetails = employeeRepository.findByEmail(employeename);
        Users user = usersRepository.findByEmail(employeename);
        GeneralResponses generalResponses;
        if(!ObjectUtils.isEmpty(primaryDetails)){
            generalResponses  = employeeRepository.getIdByEmployeename(employeename);
        }else{
            generalResponses  = usersRepository.getIdByEmployeename(employeename);
        }


        claims.put("validSession", true);
        claims.put("role", generalResponses.getROLE_NAME());
        claims.put("primaryId", generalResponses.getID());
        claims.put("emailId",employeename);
        return createToken(claims, employeename);
    }

    private String createToken(Map<String, Object> claims, String subject) {

        String jwiId = UUID.randomUUID().toString();
        JwtEntity jwtEntity =  new JwtEntity();
        jwtEntity.setJti(jwiId);
        jwtEntity.setValidSession(true) ;
        jwtEntity.setPrimaryId((Long) claims.get("primaryId")) ;
        jwtRepository.saveAndFlush(jwtEntity);

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .setId(jwiId)
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
        return extractClaim(token, Claims::getId);  // Extract JTI from the token using Claims::getId
    }
}





