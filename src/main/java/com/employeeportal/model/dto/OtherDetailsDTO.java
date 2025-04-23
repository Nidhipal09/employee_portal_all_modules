package com.employeeportal.model.dto;

import com.employeeportal.model.PrimaryDetails;
import lombok.Data;

@Data
public class OtherDetailsDTO {
    private Long primaryId;
    private Long otherId;
    private String illness;
    private String selfIntroduction;
    private PrimaryDetails primaryDetails; // Optional, if you want to include the whole object
    private Boolean declarationAccepted;
    // Constructor

}
