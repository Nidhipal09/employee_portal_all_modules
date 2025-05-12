package com.employeeportal.model.registration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResendLinkResponseDto {

    private String message;
    private String url;
}
