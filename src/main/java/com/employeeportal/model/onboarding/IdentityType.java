package com.employeeportal.model.onboarding;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum IdentityType {
    PAN, AADHAR;

    @JsonCreator
    public static IdentityType fromString(String value) {
        return value == null ? null : IdentityType.valueOf(value.toUpperCase());
    }
}
