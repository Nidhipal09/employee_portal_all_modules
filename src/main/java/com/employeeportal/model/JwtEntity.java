package com.employeeportal.model;

import javax.persistence.*;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "jwt_token")
public class JwtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "jti")
    private String jti;

    @Column(name = "valid_session")
    private Boolean validSession;
    @Column(name = "primaryId")
    private int primaryId;

}
