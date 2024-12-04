package com.project2.wanderfun.infrastructure.service;

import com.project2.wanderfun.application.mapper.ObjectMapper;
import com.project2.wanderfun.domain.model.RefreshToken;
import com.project2.wanderfun.application.repository.RefreshTokenRepository;
import com.project2.wanderfun.application.service.RefreshTokenService;
import com.project2.wanderfun.application.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenServiceImpl extends BaseServiceImpl<RefreshToken> implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public RefreshTokenServiceImpl(ObjectMapper objectMapper, RefreshTokenRepository refreshTokenRepository) {
        super(refreshTokenRepository, objectMapper, RefreshToken.class);
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    @Transactional
    public RefreshToken findByEmail(String email) {
        return refreshTokenRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", RefreshToken.class.getSimpleName())));
    }

    @Override
    @Transactional
    public void deleteByEmail(String email) {
        RefreshToken existingRefreshtoken = refreshTokenRepository.findByEmail(email).orElse(null);
        if (existingRefreshtoken != null) {
            refreshTokenRepository.deleteByEmail(email);
        }
    }
}
