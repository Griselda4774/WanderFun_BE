package com.wanderfun.infrastructurelayer.persistence.jpaRepository.auths;

import com.wanderfun.infrastructurelayer.persistence.entity.auths.RefreshTokenEntity;
import com.wanderfun.infrastructurelayer.persistence.jpaRepository.JpaBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaRefreshTokenRepository extends JpaBaseRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByAccount_Id(Long accountId);
    void deleteByAccount_Id(Long accountId);
}
