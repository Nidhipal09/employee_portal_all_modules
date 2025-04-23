package com.employeeportal.model.dto;

import lombok.Data;

@Data
public class PreviewDetailsDTO {
    private Long primaryId;
    private String employeeSignatureUrl;
    private String signatureDate;
    private String place;
}
