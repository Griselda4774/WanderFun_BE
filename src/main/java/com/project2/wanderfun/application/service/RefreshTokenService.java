package com.project2.wanderfun.application.service;

import com.project2.wanderfun.domain.model.RefreshToken;

public interface RefreshTokenService {
    public RefreshToken findRefreshTokenById(Long id);
    public RefreshToken findRefreshTokenByEmail(String email);
    public void createRefreshToken(RefreshToken refreshToken);
    public void updateRefreshTokenById(Long id, RefreshToken refreshToken);
    public void deleteRefreshTokenById(Long id);
    public void deleteRefreshTokenByEmail(String email);
}
