package com.wanderfun.infrastructurelayer.util;

import com.wanderfun.applicationlayer.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailUtilImpl implements MailUtil {
    private final JavaMailSender javaMailSender;

    @Autowired
    public MailUtilImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendOtp(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your Verification Code");
        message.setText("Your verification code is: " + otp + ". It is valid for 1 minute.");
        javaMailSender.send(message);
    }
}
