package com.project2.wanderfun.application.service;

import com.project2.wanderfun.domain.model.RefreshToken;

public interface RefreshTokenService extends BaseService<RefreshToken> {
    public RefreshToken findByEmail(String email);
    public void deleteByEmail(String email);
}
