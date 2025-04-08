package com.project2.wanderfun.application.util;

import com.project2.wanderfun.domain.model.tokens.TokenType;

import java.util.Date;

public interface JwtUtil {
    public String generateAccessToken(Long id, String email, String role);
    public String generateRefreshToken(Long id);
    public Long getIdFromToken(String token);
    public String getEmailFromToken(String token);
    public String getRoleFromToken(String token);
    public TokenType getTokenTypeFromToken(String token);
    public Date getExpirationDateFromToken(String token);
    public boolean validateToken(String token);
    public boolean isAccessToken(String token);
    public boolean isRefreshToken(String token);
}
