package com.project2.wanderfun.application.service;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.domain.model.RefreshToken;
import com.project2.wanderfun.domain.repository.RefreshTokenRepository;
import com.project2.wanderfun.domain.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final ObjectMapper objectMapper;
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public RefreshTokenServiceImpl(ObjectMapper objectMapper, RefreshTokenRepository refreshTokenRepository) {
        this.objectMapper = objectMapper;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public RefreshToken findRefreshTokenById(Long id) {
        return refreshTokenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RefreshToken not found"));
    }

    @Override
    public RefreshToken findRefreshTokenByEmail(String email) {
        return refreshTokenRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("RefreshToken not found"));
    }

    @Override
    public void createRefreshToken(RefreshToken refreshToken) {
        refreshToken.setId(null);
        refreshTokenRepository.save(refreshToken);
    }

    @Override
    public void updateRefreshTokenById(Long id, RefreshToken refreshToken) {
        refreshToken.setId(id);
        refreshTokenRepository.save(refreshToken);
    }

    @Override
    public void deleteRefreshTokenById(Long id) {
        refreshTokenRepository.deleteById(id);
    }

    @Override
    public void deleteRefreshTokenByEmail(String email) {
        refreshTokenRepository.deleteByEmail(email);
    }
}
