package com.project2.wanderfun.domain.repository;

import com.project2.wanderfun.domain.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends BaseRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByEmail(String email);
    void deleteByEmail(String email);
}
