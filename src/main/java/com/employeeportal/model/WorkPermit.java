package com.employeeportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "work_permit")
public class WorkPermit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workId;
    private Boolean legalRightToWork;
    private String workPermitDetails;
    @Temporal(TemporalType.DATE)
    private Date workPermitValidTill;
    private String passportCopy;
    private String passportCopyPath;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "primary_id", nullable = false)
    private PrimaryDetails primaryDetails;
    // Constructors
    public WorkPermit() {}

    public WorkPermit(Long workId, Boolean legalRightToWork, String workPermitDetails, Date workPermitValidTill, String passportCopy, String passportCopyPath, PrimaryDetails primaryDetails) {
        this.workId = workId;
        this.legalRightToWork = legalRightToWork;
        this.workPermitDetails = workPermitDetails;
        this.workPermitValidTill = workPermitValidTill;
        this.passportCopy = passportCopy;
        this.passportCopyPath = passportCopyPath;
        this.primaryDetails = primaryDetails;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public Boolean getLegalRightToWork() {
        return legalRightToWork;
    }

    public void setLegalRightToWork(Boolean legalRightToWork) {
        this.legalRightToWork = legalRightToWork;
    }

    public String getWorkPermitDetails() {
        return workPermitDetails;
    }

    public void setWorkPermitDetails(String workPermitDetails) {
        this.workPermitDetails = workPermitDetails;
    }

    public Date getWorkPermitValidTill() {
        return workPermitValidTill;
    }

    public void setWorkPermitValidTill(Date workPermitValidTill) {
        this.workPermitValidTill = workPermitValidTill;
    }

    public String getPassportCopy() {
        return passportCopy;
    }

    public void setPassportCopy(String passportCopy) {
        this.passportCopy = passportCopy;
    }

    public String getPassportCopyPath() {
        return passportCopyPath;
    }

    public void setPassportCopyPath(String passportCopyPath) {
        this.passportCopyPath = passportCopyPath;
    }

    public PrimaryDetails getPrimaryDetails() {
        return primaryDetails;
    }

    public void setPrimaryDetails(PrimaryDetails primaryDetails) {
        this.primaryDetails = primaryDetails;
    }
}

