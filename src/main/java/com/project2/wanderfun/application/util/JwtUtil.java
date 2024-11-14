package com.project2.wanderfun.application.util;

import com.project2.wanderfun.domain.model.enums.TokenType;

import java.util.Date;

public interface JwtUtil {
    public String generateAccessToken(Long id, String email, String role);
    public String generateRefreshToken(Long id);
    public String getIdFromToken(String token);
    public String getEmailFromToken(String token);
    public String getRoleFromToken(String accessToken);
    public Date getExpirationDateFromToken(String token);
    public boolean validateToken(String accessToken);
}
