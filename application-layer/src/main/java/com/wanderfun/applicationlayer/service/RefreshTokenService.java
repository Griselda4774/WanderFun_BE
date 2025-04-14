package com.wanderfun.applicationlayer.service;

import com.wanderfun.domainlayer.model.tokens.RefreshToken;

public interface RefreshTokenService extends BaseService<RefreshToken> {
    RefreshToken findByEmail(String email);
    void deleteByEmail(String email);
}
