package com.wanderfun.infrastructurelayer.persistence.entity.auths;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "mail_otps")
public class MailOtpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Column(name = "otp", nullable = false, length = 6, unique = true)
    private String otp;
    private LocalDateTime expirationTime;
    private boolean used;

    public MailOtpEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
