package com.wanderfun.domainlayer.repository;

import com.wanderfun.domainlayer.model.auths.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends BaseRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByAccountId(Long accountId);
    void deleteByAccountId(Long accountId);
}
