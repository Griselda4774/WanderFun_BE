package com.wanderfun.applicationlayer.usecase.auths;

import com.wanderfun.applicationlayer.dto.auths.LoginDto;
import com.wanderfun.applicationlayer.dto.auths.LoginResponseDto;
import com.wanderfun.applicationlayer.dto.auths.RegisterDto;
import com.wanderfun.applicationlayer.dto.auths.TokenResponseDto;

public interface AuthUsecase {
    boolean register(RegisterDto registerDto);
    boolean registerAdmin(RegisterDto registerDto);
    LoginResponseDto login(LoginDto loginDto);
    boolean logout(String accessToken);
    TokenResponseDto refresh (String refreshToken);
}
