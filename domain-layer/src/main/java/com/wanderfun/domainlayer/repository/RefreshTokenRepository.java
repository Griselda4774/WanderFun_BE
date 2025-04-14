package com.wanderfun.domainlayer.repository;

import com.wanderfun.domainlayer.model.tokens.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends BaseRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByEmail(String email);
    void deleteByEmail(String email);
}
