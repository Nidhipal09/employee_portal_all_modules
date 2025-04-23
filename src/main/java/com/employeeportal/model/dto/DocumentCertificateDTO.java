package com.employeeportal.model.dto;


import com.employeeportal.model.DocumentsType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Data
public class DocumentCertificateDTO {
    private Long documentId;
    @Enumerated(EnumType.STRING)
    private DocumentsType documentType;
    private Boolean degreeCertificate;
    private String degreeCertificateUrl;
    private Boolean passingCertificate;
    private String passingCertificateUrl;


}
