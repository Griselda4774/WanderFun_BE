package com.project2.wanderfun.infrastructure.repository;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.domain.model.RefreshToken;
import com.project2.wanderfun.domain.repository.RefreshTokenRepository;
import com.project2.wanderfun.infrastructure.persistence.entity.RefreshTokenEntity;
import com.project2.wanderfun.infrastructure.persistence.jpaRepository.JpaRefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RefreshTokenRepositoryImpl extends BaseRepositoryImpl<RefreshToken, RefreshTokenEntity, Long> implements RefreshTokenRepository {
    private final JpaRefreshTokenRepository jpaRefreshTokenRepository;

    @Autowired
    public RefreshTokenRepositoryImpl(JpaRefreshTokenRepository refreshTokenJpaRepository, ObjectMapper objectMapper) {
        super(refreshTokenJpaRepository, objectMapper, RefreshToken.class, RefreshTokenEntity.class);
        this.jpaRefreshTokenRepository = refreshTokenJpaRepository;
    }

    @Override
    public Optional<RefreshToken> findByEmail(String email) {
        return jpaRefreshTokenRepository.findByEmail(email)
                .map(entity -> objectMapper.map(entity, RefreshToken.class));
    }

    @Override
    public void deleteByEmail(String email) {
        jpaRefreshTokenRepository.deleteByEmail(email);
    }
}
