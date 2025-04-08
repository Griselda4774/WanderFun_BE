package com.project2.wanderfun.application.service;

import com.project2.wanderfun.domain.model.tokens.RefreshToken;

public interface RefreshTokenService extends BaseService<RefreshToken> {
    RefreshToken findByEmail(String email);
    void deleteByEmail(String email);
}
