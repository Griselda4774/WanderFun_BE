package com.wanderfun.infrastructurelayer.service.auths;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.auths.RefreshToken;
import com.wanderfun.domainlayer.repository.auths.RefreshTokenRepository;
import com.wanderfun.applicationlayer.service.auths.RefreshTokenService;
import com.wanderfun.applicationlayer.exception.ObjectNotFoundException;
import com.wanderfun.infrastructurelayer.service.BaseServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenServiceImpl extends BaseServiceImpl<RefreshToken, Long> implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public RefreshTokenServiceImpl(ObjectMapper objectMapper, RefreshTokenRepository refreshTokenRepository) {
        super(refreshTokenRepository, objectMapper, RefreshToken.class);
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    @Transactional
    public RefreshToken findByAccountId(Long accountId) {
        return refreshTokenRepository.findByAccountId(accountId)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", RefreshToken.class.getSimpleName())));
    }

    @Override
    @Transactional
    public void updateByAccountId(Long accountId, RefreshToken refreshToken) {
        RefreshToken existingRefreshToken = refreshTokenRepository.findByAccountId(accountId)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("%s not found", RefreshToken.class.getSimpleName())));

        objectMapper.copyProperties(refreshToken, existingRefreshToken);
        refreshTokenRepository.save(existingRefreshToken);
    }

    @Override
    @Transactional
    public void deleteByAccountId(Long accountId) {
        refreshTokenRepository.findByAccountId(accountId).ifPresent(existingRefreshtoken -> refreshTokenRepository.deleteByAccountId(accountId));
    }
}
