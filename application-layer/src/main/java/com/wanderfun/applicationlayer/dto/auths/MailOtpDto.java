package com.wanderfun.applicationlayer.dto.auths;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class MailOtpDto {
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "OTP is required")
    private String otp;

    public MailOtpDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
