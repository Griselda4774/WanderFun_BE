package com.project2.wanderfun.application.util;

public interface JwtUtil {
    public String generateAccessToken(String email, String role);
    public String generateRefreshToken(String email);
    public String getEmailFromAccessToken(String accessToken);
    public String getRoleFromAccessToken(String accessToken);
    public String getEmailFromRefreshToken(String refreshToken);
    public boolean validateAccessToken(String accessToken);
    public boolean validateRefreshToken(String refreshToken);
}
