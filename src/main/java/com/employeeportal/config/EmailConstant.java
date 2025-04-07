package com.employeeportal.config;

public interface EmailConstant {

    
    String SIGN_UP_LINK_TEMPLATE_NAME = "create-signUp-link";

    String SIGN_UP_LINK_SUBJECT = "Send SignUp Activate-Link";
    String RESEND_LINK_SUBJECT = "Resend Activate-Link";
    String  SIGN_UP_OTP_TEMPLATE_NAME = "otp-SignUp";

    String SIGN_UP_OTP_SUBJECT = "Send SignUp Otp";

    String ACTIVE_SIGNUP_LINK = "http://localhost:3000/enterOtp";
    // New link for password reset
    String RESET_PASSWORD_LINK = "http://localhost:3000/resetPassword";

}