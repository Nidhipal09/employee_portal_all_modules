package com.employeeportal.model.dto;

import java.util.Date;

public class WorkPermitDTO {

    private Long visaId;
    private String visaType;
    private Boolean legalRightToWork;
    private String workPermitDetails;
    private Date workPermitValidTill;
    private  String passportCopy;
    private String passportCopyPath;

    public WorkPermitDTO() {
    }

    public WorkPermitDTO(Long visaId, String visaType, Boolean legalRightToWork, String workPermitDetails, Date workPermitValidTill, String passportCopy, String passportCopyPath) {
        this.visaId = visaId;
        this.visaType = visaType;
        this.legalRightToWork = legalRightToWork;
        this.workPermitDetails = workPermitDetails;
        this.workPermitValidTill = workPermitValidTill;
        this.passportCopy = passportCopy;
        this.passportCopyPath = passportCopyPath;
    }

    public Long getVisaId() {
        return visaId;
    }

    public void setVisaId(Long visaId) {
        this.visaId = visaId;
    }

    public String getVisaType() {
        return visaType;
    }

    public void setVisaType(String visaType) {
        this.visaType = visaType;
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
}
