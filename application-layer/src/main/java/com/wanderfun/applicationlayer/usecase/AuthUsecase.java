package com.wanderfun.applicationlayer.usecase;

import com.wanderfun.applicationlayer.dto.auths.*;

public interface AuthUsecase {
    boolean register(RegisterDto registerDto);
    boolean registerAdmin(RegisterDto registerDto);
    LoginResponseDto login(LoginDto loginDto);
    boolean logout(String accessToken);
    TokenResponseDto refresh (String refreshToken);
    boolean sendOtp(String email);
    boolean verifyOtp(MailOtpDto mailOtpDto);
}
