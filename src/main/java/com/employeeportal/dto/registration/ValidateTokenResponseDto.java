package com.employeeportal.dto.registration;

public class ValidateTokenResponseDto {

    private boolean isTokenValid;
    private String message;

    public ValidateTokenResponseDto() {
        super();
    }

    public ValidateTokenResponseDto(boolean isTokenValid, String message) {
        super();
        this.isTokenValid = isTokenValid;
        this.message = message;
    }

    public boolean isTokenValid() {
        return isTokenValid;
    }

    public void setTokenValid(boolean isTokenValid) {
        this.isTokenValid = isTokenValid;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
