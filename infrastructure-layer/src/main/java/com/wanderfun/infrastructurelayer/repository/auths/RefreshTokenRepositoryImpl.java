package com.wanderfun.infrastructurelayer.repository.auths;

import com.wanderfun.applicationlayer.mapper.ObjectMapper;
import com.wanderfun.domainlayer.model.auths.RefreshToken;
import com.wanderfun.domainlayer.repository.RefreshTokenRepository;
import com.wanderfun.infrastructurelayer.persistence.entity.auths.AccountEntity;
import com.wanderfun.infrastructurelayer.persistence.entity.auths.RefreshTokenEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.auths.JpaRefreshTokenRepository;
import com.wanderfun.infrastructurelayer.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RefreshTokenRepositoryImpl extends BaseRepositoryImpl<RefreshToken, RefreshTokenEntity, Long> implements RefreshTokenRepository {
    private final JpaRefreshTokenRepository jpaRefreshTokenRepository;

    @Autowired
    public RefreshTokenRepositoryImpl(JpaRefreshTokenRepository jpaRefreshTokenRepository, ObjectMapper objectMapper) {
        super(jpaRefreshTokenRepository, objectMapper, RefreshToken.class, RefreshTokenEntity.class);
        this.jpaRefreshTokenRepository = jpaRefreshTokenRepository;
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        RefreshTokenEntity refreshTokenEntity = objectMapper.map(refreshToken, RefreshTokenEntity.class);

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(refreshToken.getAccountId());
        refreshTokenEntity.setAccount(accountEntity);

        RefreshTokenEntity savedRefreshTokenEntity = jpaRefreshTokenRepository.save(refreshTokenEntity);

        return objectMapper.map(savedRefreshTokenEntity, RefreshToken.class);
    }

    @Override
    public Optional<RefreshToken> findByAccountId(Long accountId) {
        return jpaRefreshTokenRepository.findByAccount_Id(accountId)
                .map(entity -> objectMapper.map(entity, RefreshToken.class));
    }

    @Override
    public void deleteByAccountId(Long accountId) {
        jpaRefreshTokenRepository.deleteByAccount_Id(accountId);
    }
}
