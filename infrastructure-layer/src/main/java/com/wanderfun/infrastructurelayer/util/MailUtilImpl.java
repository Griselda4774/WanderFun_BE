package com.wanderfun.infrastructurelayer.util;

import com.wanderfun.applicationlayer.util.MailUtil;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MailUtilImpl implements MailUtil {
    private final JavaMailSender javaMailSender;

    @Autowired
    public MailUtilImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Async
    public void sendOtp(String toEmail, String otp) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            String senderName = "WanderFun";
            String fromAddress = "wanderfun.noreply@gmail.com";

            helper.setFrom(new InternetAddress(fromAddress, senderName) );
            helper.setTo(toEmail);
            helper.setSubject("Your Verification Code");
            helper.setText("Your Verification is: " + otp + ". It is valid for 1 minutes.");

            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
