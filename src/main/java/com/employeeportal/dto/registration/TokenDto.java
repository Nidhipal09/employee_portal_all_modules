package com.employeeportal.dto.registration;

public class TokenDto {

    private String token;

    public TokenDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TokenDto(String token) {
        super();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
