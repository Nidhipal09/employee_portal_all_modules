package com.employeeportal.service;


import com.employeeportal.config.EmailConstant;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import com.employeeportal.config.EmailProperties;

import javax.mail.internet.MimeMessage;


@Service
public class EmailService {

    private final JavaMailSender emailSender;

    private final EmailProperties emailProperties;

    private final TemplateEngine templateEngine;


    public EmailService(JavaMailSender emailSender, EmailProperties emailProperties, TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.emailProperties = emailProperties;
        this.templateEngine = templateEngine;
    }

    public void sendEmail(String email,String createdDate, String randomSixDigitNumber, String subject, String templateName) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(emailProperties.getUserName());
            helper.setTo(email);
            Context ctx = new Context();
            ctx.setVariable("otpCode", randomSixDigitNumber);
           // ctx.setVariable("activateLink", EmailConstant.ACTIVE_SIGNUP_LINK+"?"+email+"/"+createdDate);
           // ctx.setVariable("activateLink",EmailConstant.ACTIVE_SIGNUP_LINK + "?email=" + email + "&date=" + createdDate);
            ctx.setVariable("activateLink", EmailConstant.ACTIVE_SIGNUP_LINK + "?email=" + email + "&timestamp=" + System.currentTimeMillis());
            // Add reset password link
          //  ctx.setVariable("activateLink", activationLink);
            String resetLink = EmailConstant.RESET_PASSWORD_LINK + "?token=" + randomSixDigitNumber; // Use appropriate token
            ctx.setVariable("resetLink", resetLink); // Add this line
            helper.setText(templateEngine.process(templateName, ctx), true);
            helper.setSubject(subject);
            emailSender.send(message);
        } catch (Exception e) {
            throw new MailSendException(e.getMessage());
        }
    }
    public void sendRegistrationEmail(String email, String password, String subject, String templateName) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(emailProperties.getUserName());
            helper.setTo(email);
            Context ctx = new Context();
            ctx.setVariable("email",email);
            ctx.setVariable("password", password);
            helper.setText(templateEngine.process(templateName, ctx), true);
            helper.setSubject(subject);
            emailSender.send(message);
        } catch (Exception e) {
            throw new MailSendException(e.getMessage());
        }
    }
}
