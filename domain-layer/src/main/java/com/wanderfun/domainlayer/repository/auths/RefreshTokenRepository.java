package com.wanderfun.domainlayer.repository.auths;

import com.wanderfun.domainlayer.model.auths.RefreshToken;
import com.wanderfun.domainlayer.repository.BaseRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends BaseRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByAccountId(Long accountId);
    void deleteByAccountId(Long accountId);
}
