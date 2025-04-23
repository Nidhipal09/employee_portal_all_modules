package com.employeeportal.model.dto;

import lombok.Data;

import java.util.Date;
@Data
public class WorkPermitDTO {

    private Long workId;

    private Boolean legalRightToWork;
    private String workPermitDetails;
    private Date workPermitValidTill;
    private  String passportCopy;
    private String passportCopyPath;




}
