package com.employeeportal.model;


import javax.persistence.*;
import java.time.LocalDateTime;

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
    private Long primaryId;

    public JwtEntity() {
    }

    public JwtEntity(Long id, String jti, Boolean validSession, Long primaryId) {
        this.id = id;
        this.jti = jti;
        this.validSession = validSession;
        this.primaryId = primaryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public Boolean getValidSession() {
        return validSession;
    }

    public void setValidSession(Boolean validSession) {
        this.validSession = validSession;
    }

    public Long getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(Long primaryId) {
        this.primaryId = primaryId;
    }
}







